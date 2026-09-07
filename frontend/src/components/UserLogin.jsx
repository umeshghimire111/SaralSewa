import React, { useState } from 'react';
import { authService } from '../api/authService';
import OtpVerificationModal from './OtpVerificationModal';

export default function UserLogin({
  onSwitchToRegister,
  onForgotPassword,
  onLoginSuccess,
  prefilledEmail = ''
}) {
  const [credentials, setCredentials] = useState({
    email: prefilledEmail,
    password: '',
  });
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [step, setStep] = useState('CREDENTIALS'); // 'CREDENTIALS' | 'OTP'
  const [otpError, setOtpError] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCredentials(prev => ({ ...prev, [name]: value }));
    if (error) setError('');
  };

  /**
   * Main Login Flow: Request OTP sent to user's Gmail
   */
  const handleInitiateOtpLogin = async (e) => {
    e.preventDefault();
    if (!credentials.email.trim() || !credentials.password) {
      setError('Please enter both email and password.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      // Backend sendOtp verifies password and emails a 6-digit OTP code to Gmail
      await authService.sendUserOtp({
        email: credentials.email.trim(),
        password: credentials.password
      });

      // Switch to OTP Verification Modal
      setStep('OTP');
    } catch (err) {
      setError(err.message || 'Failed to send OTP. Please check your credentials.');
    } finally {
      setLoading(false);
    }
  };

  /**
   * Direct Password Authentication (Alternative standard login)
   */
  const handleDirectPasswordLogin = async () => {
    if (!credentials.email.trim() || !credentials.password) {
      setError('Please enter both email and password.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const res = await authService.loginUser({
        email: credentials.email.trim(),
        password: credentials.password
      });

      const userSession = authService.saveSession(res, 'CUSTOMER', credentials.email);
      if (onLoginSuccess) {
        onLoginSuccess(userSession);
      }
    } catch (err) {
      setError(err.message || 'Login failed. Please check your credentials.');
    } finally {
      setLoading(false);
    }
  };

  /**
   * Verify the 6-digit OTP code entered from Gmail
   */
  const handleVerifyOtp = async (otpCode) => {
    setLoading(true);
    setOtpError('');

    try {
      const res = await authService.verifyUserOtp(credentials.email.trim(), otpCode);
      const userSession = authService.saveSession(res, 'CUSTOMER', credentials.email.trim());
      if (onLoginSuccess) {
        onLoginSuccess(userSession);
      }
    } catch (err) {
      setOtpError(err.message || 'Invalid or expired OTP code. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  /**
   * Resend OTP code to Gmail
   */
  const handleResendOtp = async () => {
    setOtpError('');
    try {
      await authService.resendUserOtp(credentials.email.trim());
    } catch (err) {
      setOtpError(err.message || 'Failed to resend OTP. Please try again.');
      throw err;
    }
  };

  if (step === 'OTP') {
    return (
      <div className="auth-card">
        <OtpVerificationModal
          email={credentials.email}
          onVerify={handleVerifyOtp}
          onResend={handleResendOtp}
          onCancel={() => setStep('CREDENTIALS')}
          loading={loading}
          error={otpError}
          isAdmin={false}
        />
      </div>
    );
  }

  return (
    <div className="auth-card">
      <div className="auth-header">
        <h1>Welcome Back</h1>
        <p>Login to your SaralSewa Customer / Provider account</p>
      </div>

      {error && (
        <div className="alert alert-error">
          <span>⚠️</span>
          <span>{error}</span>
        </div>
      )}

      <form onSubmit={handleInitiateOtpLogin}>
        <div className="form-group">
          <label className="form-label">
            Email Address <span className="req">*</span>
          </label>
          <div className="input-container">
            <span className="input-icon">✉️</span>
            <input
              type="email"
              name="email"
              placeholder="Enter your registered email"
              value={credentials.email}
              onChange={handleChange}
              className="form-input"
              required
            />
          </div>
        </div>

        <div className="form-group">
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '0.4rem' }}>
            <label className="form-label" style={{ margin: 0 }}>
              Password <span className="req">*</span>
            </label>
            <button
              type="button"
              className="auth-link"
              style={{ fontSize: '0.8rem', background: 'none', border: 'none' }}
              onClick={onForgotPassword}
            >
              Forgot Password?
            </button>
          </div>
          <div className="input-container">
            <span className="input-icon">🔒</span>
            <input
              type={showPassword ? 'text' : 'password'}
              name="password"
              placeholder="Enter your password"
              value={credentials.password}
              onChange={handleChange}
              className="form-input"
              required
            />
            <button
              type="button"
              className="password-toggle-btn"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? 'Hide' : 'Show'}
            </button>
          </div>
        </div>

        {/* Primary action: Login with Gmail OTP */}
        <button
          type="submit"
          className="btn-primary"
          disabled={loading}
          style={{ marginTop: '1.25rem' }}
        >
          {loading ? (
            <>
              <div className="spinner" />
              <span>Sending OTP to Gmail...</span>
            </>
          ) : (
            'Send OTP & Login via Gmail'
          )}
        </button>

        <div className="divider">or</div>

        {/* Secondary action: Direct password authentication */}
        <button
          type="button"
          className="btn-secondary"
          onClick={handleDirectPasswordLogin}
          disabled={loading}
        >
          Direct Password Login
        </button>
      </form>

      <div className="auth-footer">
        Don't have an account yet?{' '}
        <button
          type="button"
          className="auth-link"
          onClick={onSwitchToRegister}
          style={{ background: 'none', border: 'none' }}
        >
          Register here
        </button>
      </div>
    </div>
  );
}
