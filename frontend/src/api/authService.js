// Centralized Authentication API service for SaralSewa

const BASE_URL = '/api/v1';

/**
 * Utility to make fetch requests with proper headers and error parsing
 */
async function request(endpoint, options = {}) {
  const url = `${BASE_URL}${endpoint}`;
  
  const headers = {
    'Content-Type': 'application/json',
    ...(options.headers || {})
  };

  const token = localStorage.getItem('saralsewa_token');
  if (token) {
    headers['Authorization'] = `Bearer ${token}`;
  }

  const response = await fetch(url, {
    ...options,
    headers,
    credentials: 'include', // for HttpOnly cookies
  });

  let data;
  const contentType = response.headers.get('content-type');
  if (contentType && contentType.includes('application/json')) {
    data = await response.json();
  } else {
    data = { message: await response.text() };
  }

  if (!response.ok) {
    let errorMsg = null;
    if (data?.message && typeof data.message === 'string' && data.message.trim() && data.message !== 'Bad Request') {
      errorMsg = data.message;
    } else if (Array.isArray(data?.errors) && data.errors.length > 0) {
      errorMsg = data.errors.map(e => e.defaultMessage || e).join(', ');
    } else if (data?.error && data.error !== 'Bad Request') {
      errorMsg = data.error;
    } else if (response.status === 404) {
      errorMsg = 'No registered account found with this email address. Please check your email or register first.';
    } else if (response.status === 400) {
      errorMsg = data?.message || 'Invalid request details. Please check your inputs.';
    } else {
      errorMsg = `Server error (${response.status}).`;
    }
    const err = new Error(errorMsg);
    err.status = response.status;
    err.data = data;
    throw err;
  }

  return data;
}

export const authService = {
  // ================= USER AUTHENTICATION =================

  /**
   * User Registration (Customer or Service Provider)
   */
  async registerUser(userData) {
    return request('/auth/register', {
      method: 'POST',
      body: JSON.stringify(userData),
    });
  },

  /**
   * Direct User Password Login
   */
  async loginUser(credentials) {
    return request('/auth/login', {
      method: 'POST',
      body: JSON.stringify(credentials),
    });
  },

  /**
   * Request OTP sent to Gmail for User Login / Verification
   */
  async sendUserOtp(credentials) {
    return request('/auth/forget-password', {
      method: 'POST',
      body: JSON.stringify(credentials),
    });
  },

  /**
   * Verify 6-digit OTP for User and complete Login
   */
  async verifyUserOtp(email, otpCode) {
    return request('/auth/forget-password/verify-otp', {
      method: 'POST',
      body: JSON.stringify({ email, otpCode }),
    });
  },

  /**
   * Resend OTP to user's Gmail
   */
  async resendUserOtp(email) {
    return request('/auth/forget-password/resend-otp', {
      method: 'POST',
      body: JSON.stringify({ email }),
    });
  },

  /**
   * Request 6-digit OTP sent to Gmail for Forgot Password (EMAIL ONLY)
   */
  async forgotPassword(email) {
    try {
      return await request('/auth/forget-password/resend-otp', {
        method: 'POST',
        body: JSON.stringify({ email }),
      });
    } catch (err) {
      return await request('/auth/forget-password', {
        method: 'POST',
        body: JSON.stringify({ email }),
      });
    }
  },

  /**
   * Verify OTP and Set New Password
   */
  async verifyAndResetPassword(email, otpCode, newPassword, confirmPassword) {
    return request('/auth/forget-password/verify-otp', {
      method: 'POST',
      body: JSON.stringify({ email, otpCode, newPassword, confirmPassword }),
    });
  },

  /**
   * User Password Reset / Set New Password
   */
  async resetUserPassword(email, otpCode) {
    return request('/auth/reset-password', {
      method: 'POST',
      body: JSON.stringify({ email, otpCode }),
    });
  },

  /**
   * User Logout
   */
  async logoutUser() {
    try {
      await request('/auth/logout', { method: 'POST' });
    } catch (e) {
      console.warn('Logout request completed with warning:', e);
    } finally {
      this.clearSession();
    }
  },

  // ================= ADMIN AUTHENTICATION =================

  /**
   * Admin Direct Password Login
   */
  async loginAdmin(credentials) {
    return request('/admin/auth/login', {
      method: 'POST',
      body: JSON.stringify(credentials),
    });
  },

  /**
   * Request OTP sent to Admin Gmail
   */
  async sendAdminOtp(credentials) {
    return request('/admin/auth/forget-password', {
      method: 'POST',
      body: JSON.stringify(credentials),
    });
  },

  /**
   * Verify Admin 6-digit OTP
   */
  async verifyAdminOtp(email, otpCode) {
    return request('/admin/auth/forget-password/verify-otp', {
      method: 'POST',
      body: JSON.stringify({ email, otpCode }),
    });
  },

  /**
   * Resend Admin OTP
   */
  async resendAdminOtp(email) {
    return request('/admin/auth/forget-password/resend-otp', {
      method: 'POST',
      body: JSON.stringify({ email }),
    });
  },

  /**
   * Request 6-digit OTP sent to Admin Gmail for Forgot Password (EMAIL ONLY)
   */
  async forgotAdminPassword(email) {
    try {
      return await request('/admin/auth/forget-password/resend-otp', {
        method: 'POST',
        body: JSON.stringify({ email }),
      });
    } catch (err) {
      return await request('/admin/auth/forget-password', {
        method: 'POST',
        body: JSON.stringify({ email }),
      });
    }
  },

  /**
   * Verify Admin OTP and Set New Password
   */
  async verifyAndResetAdminPassword(email, otpCode, newPassword, confirmPassword) {
    return request('/admin/auth/forget-password/verify-otp', {
      method: 'POST',
      body: JSON.stringify({ email, otpCode, newPassword, confirmPassword }),
    });
  },

  /**
   * Admin Logout
   */
  async logoutAdmin() {
    try {
      await request('/admin/auth/logout', { method: 'POST' });
    } catch (e) {
      console.warn('Admin logout request completed with warning:', e);
    } finally {
      this.clearSession();
    }
  },

  // ================= SESSION MANAGEMENT =================

  parseJwt(token) {
    try {
      if (!token) return null;
      const base64Url = token.split('.')[1];
      if (!base64Url) return null;
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(
        window
          .atob(base64)
          .split('')
          .map((c) => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
          .join('')
      );
      return JSON.parse(jsonPayload);
    } catch {
      return null;
    }
  },

  saveSession(authData, defaultRole = 'CUSTOMER', email = '') {
    const token = authData?.data?.accessToken || authData?.accessToken || null;
    const refreshToken = authData?.data?.refreshToken || authData?.refreshToken || null;

    if (token) {
      localStorage.setItem('saralsewa_token', token);
    }
    if (refreshToken) {
      localStorage.setItem('saralsewa_refresh_token', refreshToken);
    }

    const decoded = token ? this.parseJwt(token) : null;
    const userEmail = email || decoded?.sub || authData?.data?.email || '';
    const userRole = decoded?.role || defaultRole;

    const sessionUser = {
      email: userEmail,
      role: userRole,
      loginTime: new Date().toISOString(),
    };

    localStorage.setItem('saralsewa_user', JSON.stringify(sessionUser));
    return sessionUser;
  },

  getSessionUser() {
    try {
      const data = localStorage.getItem('saralsewa_user');
      return data ? JSON.parse(data) : null;
    } catch {
      return null;
    }
  },

  clearSession() {
    localStorage.removeItem('saralsewa_token');
    localStorage.removeItem('saralsewa_refresh_token');
    localStorage.removeItem('saralsewa_user');
  }
};
