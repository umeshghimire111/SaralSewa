import React, { useState } from 'react';
import { authService } from '../api/authService';
import OtpVerificationModal from './OtpVerificationModal';

export default function AdminLogin({ onLoginSuccess, onForgotPassword }) {
  const [credentials, setCredentials] = useState({
    email: '',
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
   * Admin Login with Gmail OTP
   */
  const handleInitiateAdminOtp = async (e) => {
    e.preventDefault();
    if (!credentials.email.trim() || !credentials.password) {
      setError('Please provide admin email and password.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      await authService.sendAdminOtp({
        email: credentials.email.trim(),
        password: credentials.password
      });

      setStep('OTP');
    } catch (err) {
      setError(err.message || 'Admin authentication failed. Please verify credentials.');
    } finally {
      setLoading(false);
    }
  };

  /**
   * Direct Admin Password Login
   */
  const handleDirectAdminLogin = async () => {
    if (!credentials.email.trim() || !credentials.password) {
      setError('Please provide admin email and password.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const res = await authService.loginAdmin({
        email: credentials.email.trim(),
        password: credentials.password
      });

      const userSession = authService.saveSession(res, 'ADMIN', credentials.email.trim());
      if (onLoginSuccess) {
        onLoginSuccess(userSession);
      }
    } catch (err) {
      setError(err.message || 'Admin login failed.');
    } finally {
      setLoading(false);
    }
  };

  /**
   * Verify Admin 6-digit OTP
   */
  const handleVerifyAdminOtp = async (otpCode) => {
    setLoading(true);
    setOtpError('');

    try {
      const res = await authService.verifyAdminOtp(credentials.email.trim(), otpCode);
      const userSession = authService.saveSession(res, 'ADMIN', credentials.email.trim());
      if (onLoginSuccess) {
        onLoginSuccess(userSession);
      }
    } catch (err) {
      setOtpError(err.message || 'Invalid admin OTP code.');
    } finally {
      setLoading(false);
    }
  };

  /**
   * Resend Admin OTP
   */
  const handleResendAdminOtp = async () => {
    setOtpError('');
    try {
      await authService.resendAdminOtp(credentials.email.trim());
    } catch (err) {
      setOtpError(err.message || 'Failed to resend admin OTP.');
      throw err;
    }
  };

  if (step === 'OTP') {
    return (
      <div className="auth-card admin-theme">
        <OtpVerificationModal
          email={credentials.email}
          onVerify={handleVerifyAdminOtp}
          onResend={handleResendAdminOtp}
          onCancel={() => setStep('CREDENTIALS')}
          loading={loading}
          error={otpError}
          isAdmin={true}
        />
      </div>
    );
  }

  return (
    <div className="auth-card admin-theme">
      <div className="auth-header">
        <div style={{
          width: '56px',
          height: '56px',
          background: 'var(--admin-light)',
          color: 'var(--admin-primary)',
          borderRadius: '50%',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          fontSize: '1.75rem',
          margin: '0 auto 1rem'
        }}>
          🛡️
        </div>
        <h1>Admin Control Panel</h1>
        <p>Restricted access for system administrators only</p>
      </div>

      {error && (
        <div className="alert alert-error">
          <span>⚠️</span>
          <span>{error}</span>
        </div>
      )}

      <form onSubmit={handleInitiateAdminOtp}>
        <div className="form-group">
          <label className="form-label">
            Admin Email Address <span className="req">*</span>
          </label>
          <div className="input-container">
            <span className="input-icon">🛡️</span>
            <input
              type="email"
              name="email"
              placeholder="admin@saralsewa.com"
              value={credentials.email}
              onChange={handleChange}
              className="form-input admin-focus"
              required
            />
          </div>
        </div>

        <div className="form-group">
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '0.4rem' }}>
            <label className="form-label" style={{ margin: 0 }}>
              Master Password <span className="req">*</span>
            </label>
            <button
              type="button"
              className="auth-link admin-link"
              style={{ fontSize: '0.8rem', background: 'none', border: 'none' }}
              onClick={onForgotPassword}
            >
              Forget Password?
            </button>
          </div>
          <div className="input-container">
            <span className="input-icon">🔒</span>
            <input
              type={showPassword ? 'text' : 'password'}
              name="password"
              placeholder="••••••••••••"
              value={credentials.password}
              onChange={handleChange}
              className="form-input admin-focus"
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

        <button
          type="submit"
          className="btn-primary admin-btn"
          disabled={loading}
          style={{ marginTop: '1.25rem' }}
        >
          {loading ? (
            <>
              <div className="spinner" />
              <span>Authenticating Admin...</span>
            </>
          ) : (
            'Verify via Gmail OTP & Enter'
          )}
        </button>

        <div className="divider">or</div>

        <button
          type="button"
          className="btn-secondary"
          onClick={handleDirectAdminLogin}
          disabled={loading}
        >
          Direct Admin Login
        </button>
      </form>
    </div>
  );
}
