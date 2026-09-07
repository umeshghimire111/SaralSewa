import React, { useState } from 'react';
import { authService } from '../api/authService';
import PasswordRequirements from './PasswordRequirements';

export default function ForgetPasswordModal({ onClose, onResetSuccess, isAdmin = false, isInline = false }) {
  const [step, setStep] = useState('EMAIL'); // 'EMAIL' | 'VERIFY_AND_SET_PASSWORD'
  const [email, setEmail] = useState('');
  const [otpCode, setOtpCode] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  /**
   * Step 1: Send OTP to Email (NO password required!)
   */
  const handleSendOtp = async (e) => {
    e.preventDefault();
    if (!email.trim() || !/\S+@\S+\.\S+/.test(email)) {
      setError('Please provide a valid registered email address.');
      return;
    }

    setLoading(true);
    setError('');
    setSuccess('');

    try {
      if (isAdmin) {
        await authService.forgotAdminPassword(email.trim());
      } else {
        await authService.forgotPassword(email.trim());
      }
      setStep('VERIFY_AND_SET_PASSWORD');
      setSuccess('6-digit OTP verification code sent to your Gmail.');
    } catch (err) {
      setError(err.message || 'Could not send OTP. Please ensure this email is registered.');
    } finally {
      setLoading(false);
    }
  };

  /**
   * Step 2: Verify OTP and Set New Password
   */
  const handleVerifyAndReset = async (e) => {
    e.preventDefault();
    if (!otpCode || otpCode.length !== 6) {
      setError('Please enter the 6-digit OTP code received in your Gmail.');
      return;
    }

    const pwdRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$/;
    if (!newPassword || newPassword.length < 6 || !pwdRegex.test(newPassword)) {
      setError('New password must meet all the security rules below.');
      return;
    }

    if (newPassword !== confirmPassword) {
      setError('New password and confirm password do not match.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      let res;
      if (isAdmin) {
        res = await authService.verifyAndResetAdminPassword(
          email.trim(),
          otpCode.trim(),
          newPassword,
          confirmPassword
        );
      } else {
        res = await authService.verifyAndResetPassword(
          email.trim(),
          otpCode.trim(),
          newPassword,
          confirmPassword
        );
      }

      setSuccess('Password reset successfully! You can now sign in with your new password.');
      
      setTimeout(() => {
        if (onResetSuccess) {
          onResetSuccess(email.trim());
        } else if (onClose) {
          onClose();
        }
      }, 1500);
    } catch (err) {
      setError(err.message || 'Failed to reset password. Please check the OTP code.');
    } finally {
      setLoading(false);
    }
  };

  const content = (
    <div className={isInline ? '' : `auth-card ${isAdmin ? 'admin-theme' : ''}`} style={isInline ? {} : { maxWidth: '480px', width: '100%' }}>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.25rem' }}>
        <h2 style={{ fontSize: '1.4rem', fontWeight: 700, margin: 0, color: 'var(--secondary)' }}>
          {isAdmin ? '🛡️ Admin Forget Password' : '🔑 Forget Password'}
        </h2>
        {onClose && (
          <button
            type="button"
            onClick={onClose}
            style={{
              background: 'none',
              border: 'none',
              fontSize: '1.5rem',
              cursor: 'pointer',
              color: 'var(--text-muted)'
            }}
          >
            ✕
          </button>
        )}
      </div>

      {error && (
        <div className="alert alert-error">
          <span>⚠️</span>
          <span>{error}</span>
        </div>
      )}

      {success && (
        <div className="alert alert-success">
          <span>✓</span>
          <span>{success}</span>
        </div>
      )}

      {step === 'EMAIL' ? (
        /* STEP 1: ONLY EMAIL */
        <form onSubmit={handleSendOtp}>
          <p style={{ color: 'var(--text-muted)', fontSize: '0.9rem', marginBottom: '1.5rem', lineHeight: '1.5' }}>
            Enter your registered email address. We will send a 6-digit OTP code to your Gmail to verify your identity.
          </p>

          <div className="form-group">
            <label className="form-label">
              Registered Email Address <span className="req">*</span>
            </label>
            <div className="input-container">
              <span className="input-icon">✉️</span>
              <input
                type="email"
                placeholder="name@example.com"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                  if (error) setError('');
                }}
                className={`form-input ${isAdmin ? 'admin-focus' : ''}`}
                required
                autoFocus
              />
            </div>
          </div>

          <button
            type="submit"
            className={`btn-primary ${isAdmin ? 'admin-btn' : ''}`}
            disabled={loading || !email.trim()}
            style={{ marginTop: '1.25rem' }}
          >
            {loading ? (
              <>
                <div className="spinner" />
                <span>Sending OTP to Gmail...</span>
              </>
            ) : (
              'Send 6-Digit OTP to Gmail'
            )}
          </button>

          <div style={{ marginTop: '1.5rem', textAlign: 'center' }}>
            <button
              type="button"
              className="auth-link"
              style={{ fontSize: '0.875rem', background: 'none', border: 'none' }}
              onClick={onClose}
            >
              ← Back to Sign In
            </button>
          </div>
        </form>
      ) : (
        /* STEP 2: OTP + NEW PASSWORD + CONFIRM PASSWORD */
        <form onSubmit={handleVerifyAndReset}>
          <p style={{ color: 'var(--text-muted)', fontSize: '0.875rem', marginBottom: '1.25rem' }}>
            We've sent a 6-digit code to <strong style={{ color: 'var(--secondary)' }}>{email}</strong>.
            Enter the code and choose your new password:
          </p>

          <div className="form-group">
            <label className="form-label">
              6-Digit Gmail OTP Code <span className="req">*</span>
            </label>
            <input
              type="text"
              inputMode="numeric"
              placeholder="123456"
              maxLength={6}
              value={otpCode}
              onChange={(e) => {
                setOtpCode(e.target.value.replace(/\D/g, ''));
                if (error) setError('');
              }}
              className="form-input no-icon"
              style={{
                textAlign: 'center',
                fontSize: '1.4rem',
                letterSpacing: '6px',
                fontWeight: '700'
              }}
              required
              autoFocus
            />
          </div>

          <div className="form-row">
            <div className="form-group">
              <label className="form-label">
                New Password <span className="req">*</span>
              </label>
              <div className="input-container">
                <span className="input-icon">🔒</span>
                <input
                  type={showPassword ? 'text' : 'password'}
                  placeholder="New password"
                  value={newPassword}
                  onChange={(e) => {
                    setNewPassword(e.target.value);
                    if (error) setError('');
                  }}
                  className={`form-input ${isAdmin ? 'admin-focus' : ''}`}
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

            <div className="form-group">
              <label className="form-label">
                Confirm Password <span className="req">*</span>
              </label>
              <div className="input-container">
                <span className="input-icon">🔒</span>
                <input
                  type={showPassword ? 'text' : 'password'}
                  placeholder="Confirm password"
                  value={confirmPassword}
                  onChange={(e) => {
                    setConfirmPassword(e.target.value);
                    if (error) setError('');
                  }}
                  className={`form-input ${isAdmin ? 'admin-focus' : ''}`}
                  required
                />
              </div>
            </div>
          </div>

          <PasswordRequirements password={newPassword} />

          <button
            type="submit"
            className={`btn-primary ${isAdmin ? 'admin-btn' : ''}`}
            disabled={loading || otpCode.length !== 6 || !newPassword}
            style={{ marginTop: '0.75rem' }}
          >
            {loading ? (
              <>
                <div className="spinner" />
                <span>Resetting Password...</span>
              </>
            ) : (
              'Reset Password & Sign In'
            )}
          </button>

          <div style={{ marginTop: '1.25rem', textAlign: 'center' }}>
            <button
              type="button"
              className="auth-link"
              style={{ fontSize: '0.85rem', background: 'none', border: 'none' }}
              onClick={() => {
                setStep('EMAIL');
                setOtpCode('');
                setNewPassword('');
                setConfirmPassword('');
                setError('');
              }}
            >
              ← Change Email / Resend Code
            </button>
          </div>
        </form>
      )}
    </div>
  );

  if (isInline) {
    return content;
  }

  return (
    <div style={{
      position: 'fixed',
      inset: 0,
      background: 'rgba(15, 23, 42, 0.65)',
      backdropFilter: 'blur(4px)',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      zIndex: 200,
      padding: '1rem'
    }}>
      {content}
    </div>
  );
}
