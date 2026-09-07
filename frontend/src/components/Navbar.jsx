import React from 'react';

export default function Navbar({ activePortal, setActivePortal, currentUser, onLogout }) {
  const isAdmin = activePortal === 'admin';

  return (
    <header className="navbar">
      <div className="nav-brand" onClick={() => setActivePortal(isAdmin ? 'admin' : 'user')}>
        <div className={`brand-icon ${isAdmin ? 'admin-theme' : ''}`}>
          {isAdmin ? '⚙' : '⚡'}
        </div>
        <div className="brand-text">
          Saral<span style={{ color: isAdmin ? 'var(--admin-primary)' : 'var(--primary)' }}>Sewa</span>
          <span className={`brand-badge ${isAdmin ? 'admin-badge' : ''}`}>
            {isAdmin ? 'Admin Portal' : 'User Portal'}
          </span>
        </div>
      </div>

      {!currentUser && (
        <div className="portal-switch-container">
          <button
            type="button"
            className={`portal-tab-btn ${!isAdmin ? 'active' : ''}`}
            onClick={() => setActivePortal('user')}
          >
            <span>👤</span>
            <span>User Portal</span>
          </button>
          <button
            type="button"
            className={`portal-tab-btn ${isAdmin ? 'active admin-active' : ''}`}
            onClick={() => setActivePortal('admin')}
          >
            <span>🛡️</span>
            <span>Admin Portal</span>
          </button>
        </div>
      )}

      {currentUser && (
        <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
          <span style={{ fontSize: '0.875rem', fontWeight: 600, color: 'var(--text-muted)' }}>
            {currentUser.email}
          </span>
          <button
            type="button"
            onClick={onLogout}
            style={{
              padding: '0.4rem 0.9rem',
              borderRadius: 'var(--radius-md)',
              border: '1px solid var(--border-color)',
              background: 'white',
              fontSize: '0.85rem',
              fontWeight: 600,
              cursor: 'pointer',
              color: 'var(--danger)'
            }}
          >
            Logout
          </button>
        </div>
      )}
    </header>
  );
}
