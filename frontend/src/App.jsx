import React, { useState, useEffect } from 'react';
import Navbar from './components/Navbar';
import UserLogin from './components/UserLogin';
import UserRegister from './components/UserRegister';
import AdminLogin from './components/AdminLogin';
import ForgetPasswordModal from './components/ForgetPasswordModal.jsx';
import DashboardView from './components/DashboardView';
import { authService } from './api/authService';

export default function App() {
  const [activePortal, setActivePortal] = useState('user'); // 'user' | 'admin'
  const [userAuthMode, setUserAuthMode] = useState('login'); // 'login' | 'register' | 'forgot'
  const [prefilledEmail, setPrefilledEmail] = useState('');
  const [currentUser, setCurrentUser] = useState(null);
  const [isForgotPasswordModalOpen, setIsForgotPasswordModalOpen] = useState(false);

  useEffect(() => {
    // Check if session already exists
    const session = authService.getSessionUser();
    if (session) {
      setCurrentUser(session);
      if (session.role === 'ADMIN') {
        setActivePortal('admin');
      }
    }
  }, []);

  const handleLoginSuccess = (userSession) => {
    setCurrentUser(userSession);
  };

  const handleRegisterSuccess = (registeredEmail) => {
    setPrefilledEmail(registeredEmail);
    // Switch to login tab so the user can immediately log in with OTP
    setUserAuthMode('login');
  };

  const handleLogout = async () => {
    if (activePortal === 'admin') {
      await authService.logoutAdmin();
    } else {
      await authService.logoutUser();
    }
    setCurrentUser(null);
  };

  return (
    <div className="app-container">
      <Navbar
        activePortal={activePortal}
        setActivePortal={(portal) => {
          setActivePortal(portal);
          setUserAuthMode('login');
        }}
        currentUser={currentUser}
        onLogout={handleLogout}
      />

      <main className="main-content">
        {currentUser ? (
          <DashboardView user={currentUser} onLogout={handleLogout} />
        ) : activePortal === 'admin' ? (
          <div className="auth-wrapper">
            <AdminLogin
              onLoginSuccess={handleLoginSuccess}
              onForgotPassword={() => setIsForgotPasswordModalOpen(true)}
            />
          </div>
        ) : (
          <div className={`auth-wrapper ${userAuthMode === 'register' ? 'wide' : ''}`}>
            {/* Quick Mode Toggle for User Portal */}
            <div style={{
              display: 'flex',
              justifyContent: 'center',
              marginBottom: '1.25rem',
              background: '#e2e8f0',
              padding: '4px',
              borderRadius: 'var(--radius-full)',
              width: 'fit-content',
              margin: '0 auto 1.5rem auto'
            }}>
              <button
                type="button"
                className={`portal-tab-btn ${userAuthMode === 'login' ? 'active' : ''}`}
                onClick={() => setUserAuthMode('login')}
              >
                Sign In
              </button>
              <button
                type="button"
                className={`portal-tab-btn ${userAuthMode === 'register' ? 'active' : ''}`}
                onClick={() => setUserAuthMode('register')}
              >
                Register
              </button>
              <button
                type="button"
                className={`portal-tab-btn ${userAuthMode === 'forgot' ? 'active' : ''}`}
                onClick={() => setUserAuthMode('forgot')}
              >
                Forgot Password
              </button>
            </div>

            {userAuthMode === 'login' && (
              <UserLogin
                onSwitchToRegister={() => setUserAuthMode('register')}
                onForgotPassword={() => setUserAuthMode('forgot')}
                onLoginSuccess={handleLoginSuccess}
                prefilledEmail={prefilledEmail}
              />
            )}

            {userAuthMode === 'register' && (
              <UserRegister
                onSwitchToLogin={() => setUserAuthMode('login')}
                onRegisterSuccess={handleRegisterSuccess}
              />
            )}

            {userAuthMode === 'forgot' && (
              <div className="auth-card">
                <ForgetPasswordModal
                  onClose={() => setUserAuthMode('login')}
                  isAdmin={false}
                  isInline={true}
                />
              </div>
            )}
          </div>
        )}
      </main>

      {isForgotPasswordModalOpen && (
        <ForgetPasswordModal
          onClose={() => setIsForgotPasswordModalOpen(false)}
          isAdmin={activePortal === 'admin'}
          isInline={false}
        />
      )}
    </div>
  );
}
