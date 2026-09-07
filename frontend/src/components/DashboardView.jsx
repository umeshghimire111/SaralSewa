import React from 'react';

export default function DashboardView({ user, onLogout }) {
  const isAdmin = user?.role === 'ADMIN';

  return (
    <div className="dashboard-card">
      <div className={`user-avatar-badge ${isAdmin ? 'admin' : ''}`}>
        {user?.email ? user.email.charAt(0).toUpperCase() : 'U'}
      </div>

      <h1 style={{ fontSize: '1.75rem', fontWeight: 800, color: 'var(--secondary)' }}>
        Welcome to SaralSewa
      </h1>
      <p style={{ color: 'var(--text-muted)', fontSize: '0.95rem' }}>
        You are authenticated and your session is active.
      </p>

      <div>
        <span className={`badge-pill ${
          isAdmin ? 'badge-admin' : user?.role === 'PROVIDER' ? 'badge-provider' : 'badge-customer'
        }`}>
          {user?.role || 'CUSTOMER'} ACCOUNT
        </span>
      </div>

      <div className="info-grid">
        <div className="info-item">
          <div className="info-label">Account Email</div>
          <div className="info-value">{user?.email || 'N/A'}</div>
        </div>

        <div className="info-item">
          <div className="info-label">Security & OTP Status</div>
          <div className="info-value" style={{ color: 'var(--accent)' }}>
            ✓ Gmail OTP Verified
          </div>
        </div>

        <div className="info-item">
          <div className="info-label">Access Level</div>
          <div className="info-value">
            {isAdmin ? '🛡️ Administrator Access' : user?.role === 'PROVIDER' ? '🛠️ Service Provider' : '🛒 Customer'}
          </div>
        </div>

        <div className="info-item">
          <div className="info-label">Login Time</div>
          <div className="info-value">
            {user?.loginTime ? new Date(user.loginTime).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) : 'Active'}
          </div>
        </div>
      </div>

      <div style={{ display: 'flex', gap: '1rem', marginTop: '1.5rem' }}>
        <button
          type="button"
          className="btn-secondary"
          onClick={() => alert(`Session details:\nRole: ${user?.role}\nEmail: ${user?.email}`)}
        >
          View Token Status
        </button>
        <button
          type="button"
          className={`btn-primary ${isAdmin ? 'admin-btn' : ''}`}
          onClick={onLogout}
          style={{ background: 'var(--danger)' }}
        >
          Logout from Session
        </button>
      </div>
    </div>
  );
}
