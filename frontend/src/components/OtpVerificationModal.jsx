import React, { useState, useEffect, useRef } from 'react';

export default function OtpVerificationModal({
  email,
  onVerify,
  onResend,
  onCancel,
  loading,
  error,
  isAdmin = false,
  expiryMinutes = 5
}) {
  const [digits, setDigits] = useState(['', '', '', '', '', '']);
  const [timeLeft, setTimeLeft] = useState(expiryMinutes * 60);
  const [resending, setResending] = useState(false);
  const inputRefs = useRef([]);

  useEffect(() => {
    // Focus first input box on render
    if (inputRefs.current[0]) {
      inputRefs.current[0].focus();
    }

    const timer = setInterval(() => {
      setTimeLeft(prev => (prev > 0 ? prev - 1 : 0));
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const handleChange = (index, value) => {
    // Only allow single digit
    const cleaned = value.replace(/\D/g, '').slice(-1);
    const newDigits = [...digits];
    newDigits[index] = cleaned;
    setDigits(newDigits);

    // Auto advance to next input
    if (cleaned && index < 5 && inputRefs.current[index + 1]) {
      inputRefs.current[index + 1].focus();
    }
  };

  const handleKeyDown = (index, e) => {
    if (e.key === 'Backspace' && !digits[index] && index > 0) {
      // Go back to previous box on backspace
      inputRefs.current[index - 1].focus();
    }
  };

  const handlePaste = (e) => {
    e.preventDefault();
    const pasteData = e.clipboardData.getData('text').replace(/\D/g, '').slice(0, 6);
    if (pasteData) {
      const newDigits = [...digits];
      for (let i = 0; i < 6; i++) {
        newDigits[i] = pasteData[i] || '';
      }
      setDigits(newDigits);
      const nextFocus = Math.min(pasteData.length, 5);
      if (inputRefs.current[nextFocus]) {
        inputRefs.current[nextFocus].focus();
      }
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const otpCode = digits.join('');
    if (otpCode.length === 6) {
      onVerify(otpCode);
    }
  };

  const handleResendClick = async () => {
    setResending(true);
    try {
      await onResend();
      setTimeLeft(expiryMinutes * 60);
      setDigits(['', '', '', '', '', '']);
      if (inputRefs.current[0]) inputRefs.current[0].focus();
    } finally {
      setResending(false);
    }
  };

  const formatTime = (seconds) => {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${mins}:${secs < 10 ? '0' : ''}${secs}`;
  };

  const isComplete = digits.join('').length === 6;

  return (
    <div className="otp-container">
      <div className="gmail-icon-wrapper">
        ✉️
      </div>

      <div className="auth-header">
        <h1>Gmail OTP Verification</h1>
        <p>
          We've sent a 6-digit verification code to:
          <br />
          <strong style={{ color: 'var(--secondary)', wordBreak: 'break-all' }}>{email}</strong>
        </p>
      </div>

      {error && (
        <div className="alert alert-error" style={{ width: '100%' }}>
          <span>⚠️</span>
          <span>{error}</span>
        </div>
      )}

      <form onSubmit={handleSubmit} style={{ width: '100%' }}>
        <div className="otp-inputs-grid" onPaste={handlePaste}>
          {digits.map((digit, index) => (
            <input
              key={index}
              ref={(el) => (inputRefs.current[index] = el)}
              type="text"
              inputMode="numeric"
              maxLength={1}
              value={digit}
              onChange={(e) => handleChange(index, e.target.value)}
              onKeyDown={(e) => handleKeyDown(index, e)}
              className={`otp-box ${digit ? 'filled' : ''}`}
              autoComplete="one-time-code"
            />
          ))}
        </div>

        <button
          type="submit"
          className={`btn-primary ${isAdmin ? 'admin-btn' : ''}`}
          disabled={!isComplete || loading || timeLeft === 0}
        >
          {loading ? (
            <>
              <div className="spinner" />
              <span>Verifying OTP...</span>
            </>
          ) : (
            'Verify & Login'
          )}
        </button>

        <div className="otp-resend-row">
          <span>
            {timeLeft > 0 ? (
              <>Expires in: <strong style={{ color: 'var(--secondary)' }}>{formatTime(timeLeft)}</strong></>
            ) : (
              <span style={{ color: 'var(--danger)' }}>Code expired!</span>
            )}
          </span>

          <button
            type="button"
            className="resend-btn"
            onClick={handleResendClick}
            disabled={resending || timeLeft > 240} // Allow resend after 1 min or expiry
          >
            {resending ? 'Sending...' : 'Resend OTP'}
          </button>
        </div>

        <div style={{ marginTop: '1.5rem', textAlign: 'center' }}>
          <button
            type="button"
            onClick={onCancel}
            style={{
              background: 'none',
              border: 'none',
              color: 'var(--text-muted)',
              fontSize: '0.85rem',
              cursor: 'pointer'
            }}
          >
            ← Back to Login Credentials
          </button>
        </div>
      </form>
    </div>
  );
}
