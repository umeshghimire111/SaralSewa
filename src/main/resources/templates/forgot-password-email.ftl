<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Reset Your SaralSewa Password</title>
  <style>
    body { font-family: 'Segoe UI', Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 20px; }
    .container { max-width: 520px; margin: 0 auto; background: #ffffff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 15px rgba(0,0,0,0.08); border: 1px solid #e2e8f0; }
    .header { background: linear-gradient(135deg, #7c3aed, #4f46e5); padding: 30px 20px; text-align: center; color: #ffffff; }
    .header h1 { margin: 0; font-size: 24px; font-weight: 700; letter-spacing: 0.5px; }
    .content { padding: 30px 25px; color: #334155; line-height: 1.6; }
    .greeting { font-size: 18px; font-weight: 600; margin-bottom: 12px; }
    .otp-box { background: #f5f3ff; border: 2px dashed #7c3aed; border-radius: 8px; padding: 20px; text-align: center; margin: 25px 0; }
    .otp-code { font-size: 36px; font-weight: 800; letter-spacing: 8px; color: #6d28d9; }
    .expiry { font-size: 13px; color: #64748b; margin-top: 8px; }
    .footer { background: #f8fafc; padding: 15px 25px; font-size: 12px; color: #94a3b8; text-align: center; border-top: 1px solid #e2e8f0; }
  </style>
</head>
<body>
  <div class="container">
    <div class="header">
      <h1>🔑 SaralSewa Password Reset</h1>
    </div>
    <div class="content">
      <div class="greeting">Hello, ${firstName!"Valued User"}!</div>
      <p>We received a request to reset your password. Use the verification code below to proceed:</p>
      
      <div class="otp-box">
        <div class="otp-code">${otpCode}</div>
        <div class="expiry">This code is valid for ${expiryMinutes!5} minutes.</div>
      </div>
      
      <p style="font-size: 13px; color: #64748b;">
        If you did not request a password reset, please secure your account or ignore this email.
      </p>
    </div>
    <div class="footer">
      &copy; 2026 SaralSewa. All rights reserved. Service at your doorstep.
    </div>
  </div>
</body>
</html>
