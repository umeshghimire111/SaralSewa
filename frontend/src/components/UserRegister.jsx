import React, { useState } from 'react';
import { authService } from '../api/authService';
import PasswordRequirements from './PasswordRequirements';

export default function UserRegister({ onSwitchToLogin, onRegisterSuccess }) {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    password: '',
    confirmPassword: '',
    address: '',
    role: 'CUSTOMER', // 'CUSTOMER' or 'PROVIDER'
    description: '',
  });

  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    if (error) setError('');
  };

  const validate = () => {
    if (!formData.firstName.trim() || formData.firstName.length < 2) {
      setError('First name must be at least 2 characters.');
      return false;
    }
    if (!formData.email.trim() || !/\S+@\S+\.\S+/.test(formData.email)) {
      setError('Please provide a valid email address.');
      return false;
    }
    if (!formData.phone || !/^\d{10}$/.test(formData.phone)) {
      setError('Phone number must be exactly 10 digits (e.g. 9800000000).');
      return false;
    }
    const pwdRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$/;
    if (!formData.password || formData.password.length < 6 || !pwdRegex.test(formData.password)) {
      setError('Password does not meet all security requirements listed below.');
      return false;
    }
    if (formData.password !== formData.confirmPassword) {
      setError('Passwords do not match.');
      return false;
    }
    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    setLoading(true);
    setError('');
    setSuccess('');

    try {
      const payload = {
        firstName: formData.firstName.trim(),
        lastName: formData.lastName.trim(),
        email: formData.email.trim(),
        phone: formData.phone.trim(),
        password: formData.password,
        confirmPassword: formData.confirmPassword,
        address: formData.address.trim(),
        role: formData.role,
        description: formData.description.trim()
      };

      const res = await authService.registerUser(payload);
      setSuccess(res?.message || 'Registration successful! You can now log in.');
      if (onRegisterSuccess) {
        onRegisterSuccess(formData.email);
      }
    } catch (err) {
      setError(err.message || 'Registration failed. Please check your details.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-card">
      <div className="auth-header">
        <h1>Create Your Account</h1>
        <p>Join SaralSewa as a Customer or Service Provider</p>
      </div>

      {/* Role Switcher */}
      <div className="role-picker">
        <button
          type="button"
          className={`role-card-btn ${formData.role === 'CUSTOMER' ? 'selected' : ''}`}
          onClick={() => setFormData(prev => ({ ...prev, role: 'CUSTOMER' }))}
        >
          <span className="role-card-icon">🛒</span>
          <span className="role-card-title">Customer</span>
          <span className="role-card-desc">Book household & repair services</span>
        </button>

        <button
          type="button"
          className={`role-card-btn ${formData.role === 'PROVIDER' ? 'selected' : ''}`}
          onClick={() => setFormData(prev => ({ ...prev, role: 'PROVIDER' }))}
        >
          <span className="role-card-icon">🛠️</span>
          <span className="role-card-title">Service Provider</span>
          <span className="role-card-desc">Offer your skills & earn</span>
        </button>
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
          <div>
            <strong>{success}</strong>
            <div style={{ marginTop: '0.25rem' }}>
              <button
                type="button"
                className="auth-link"
                style={{ fontSize: '0.85rem' }}
                onClick={onSwitchToLogin}
              >
                Click here to Log in now →
              </button>
            </div>
          </div>
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <div className="form-group">
            <label className="form-label">
              First Name <span className="req">*</span>
            </label>
            <input
              type="text"
              name="firstName"
              placeholder="e.g. Ramesh"
              value={formData.firstName}
              onChange={handleChange}
              className="form-input no-icon"
              required
            />
          </div>

          <div className="form-group">
            <label className="form-label">Last Name</label>
            <input
              type="text"
              name="lastName"
              placeholder="e.g. Sharma"
              value={formData.lastName}
              onChange={handleChange}
              className="form-input no-icon"
            />
          </div>
        </div>

        <div className="form-group">
          <label className="form-label">
            Email Address <span className="req">*</span>
          </label>
          <div className="input-container">
            <span className="input-icon">✉️</span>
            <input
              type="email"
              name="email"
              placeholder="name@example.com"
              value={formData.email}
              onChange={handleChange}
              className="form-input"
              required
            />
          </div>
        </div>

        <div className="form-row">
          <div className="form-group">
            <label className="form-label">
              Phone Number <span className="req">*</span>
            </label>
            <div className="input-container">
              <span className="input-icon">📱</span>
              <input
                type="tel"
                name="phone"
                placeholder="10 digit mobile"
                maxLength={10}
                value={formData.phone}
                onChange={handleChange}
                className="form-input"
                required
              />
            </div>
          </div>

          <div className="form-group">
            <label className="form-label">City / Address</label>
            <div className="input-container">
              <span className="input-icon">📍</span>
              <input
                type="text"
                name="address"
                placeholder="e.g. Kathmandu"
                value={formData.address}
                onChange={handleChange}
                className="form-input"
              />
            </div>
          </div>
        </div>

        {formData.role === 'PROVIDER' && (
          <div className="form-group">
            <label className="form-label">Skills & Service Description</label>
            <textarea
              name="description"
              placeholder="Describe your expertise (e.g. Electrician, Plumbing, AC Repair with 5 years experience)..."
              value={formData.description}
              onChange={handleChange}
              className="form-textarea"
            />
          </div>
        )}

        <div className="form-row">
          <div className="form-group">
            <label className="form-label">
              Password <span className="req">*</span>
            </label>
            <div className="input-container">
              <span className="input-icon">🔒</span>
              <input
                type={showPassword ? 'text' : 'password'}
                name="password"
                placeholder="••••••••"
                value={formData.password}
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

          <div className="form-group">
            <label className="form-label">
              Confirm Password <span className="req">*</span>
            </label>
            <div className="input-container">
              <span className="input-icon">🔒</span>
              <input
                type={showPassword ? 'text' : 'password'}
                name="confirmPassword"
                placeholder="••••••••"
                value={formData.confirmPassword}
                onChange={handleChange}
                className="form-input"
                required
              />
            </div>
          </div>
        </div>

        <PasswordRequirements password={formData.password} />

        <button
          type="submit"
          className="btn-primary"
          disabled={loading}
          style={{ marginTop: '0.5rem' }}
        >
          {loading ? (
            <>
              <div className="spinner" />
              <span>Registering Account...</span>
            </>
          ) : (
            `Register as ${formData.role === 'PROVIDER' ? 'Service Provider' : 'Customer'}`
          )}
        </button>
      </form>

      <div className="auth-footer">
        Already have an account?{' '}
        <button
          type="button"
          className="auth-link"
          onClick={onSwitchToLogin}
          style={{ background: 'none', border: 'none' }}
        >
          Log In here
        </button>
      </div>
    </div>
  );
}
