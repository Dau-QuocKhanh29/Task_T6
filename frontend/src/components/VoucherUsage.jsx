import { useState, useEffect } from 'react';
import { getUsers } from '../services/userService';
import { getVouchers } from '../services/voucherService';
import { useVoucher, getVoucherUsages } from '../services/voucherUsageService';
import '../styles/VoucherUsage.css';

function VoucherUsage() {
  const [users, setUsers] = useState([]);
  const [vouchers, setVouchers] = useState([]);
  const [usages, setUsages] = useState([]);
  const [loading, setLoading] = useState(false);
  const [selectedUser, setSelectedUser] = useState('');
  const [selectedVoucher, setSelectedVoucher] = useState('');
  const [submitted, setSubmitted] = useState(false);

  // Load dữ liệu
  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    setLoading(true);
    try {
      const [usersData, vouchersData, usagesData] = await Promise.all([
        getUsers(),
        getVouchers(),
        getVoucherUsages(),
      ]);
      setUsers(Array.isArray(usersData) ? usersData : []);
      setVouchers(Array.isArray(vouchersData) ? vouchersData : []);
      setUsages(Array.isArray(usagesData) ? usagesData : []);
    } catch (error) {
      console.error('Error loading data:', error);
      alert('Lỗi tải dữ liệu');
    } finally {
      setLoading(false);
    }
  };

  const handleUseVoucher = async () => {
    if (!selectedUser || !selectedVoucher) {
      alert('Vui lòng chọn người dùng và voucher');
      return;
    }

    try {
      await useVoucher({
        userId: parseInt(selectedUser),
        voucherId: parseInt(selectedVoucher),
      });
      alert('Sử dụng voucher thành công');
      setSelectedUser('');
      setSelectedVoucher('');
      setSubmitted(true);
      setTimeout(() => setSubmitted(false), 3000);
      loadData();
    } catch (error) {
      alert('Lỗi: ' + error.message);
    }
  };

  const selectedUserData = users.find(u => u.id === parseInt(selectedUser));
  const selectedVoucherData = vouchers.find(v => v.id === parseInt(selectedVoucher));

  return (
    <div className="voucher-usage-container">
      <h2>Sử Dụng Voucher</h2>

      {submitted && (
        <div className="success-message">
          ✓ Sử dụng voucher thành công cho {selectedUserData?.fullName}
        </div>
      )}

      <div className="usage-form">
        <div className="form-section">
          <div className="form-group">
            <label>Chọn Người Dùng:</label>
            <select
              value={selectedUser}
              onChange={(e) => setSelectedUser(e.target.value)}
              className="select-dropdown"
            >
              <option value="">-- Chọn người dùng --</option>
              {users.map(user => (
                <option key={user.id} value={user.id}>
                  {user.fullName} ({user.email})
                </option>
              ))}
            </select>
            {selectedUserData && (
              <div className="selected-info user-info">
                <p><strong>Họ Tên:</strong> {selectedUserData.fullName}</p>
                <p><strong>Email:</strong> {selectedUserData.email}</p>
                <p><strong>Số Điện Thoại:</strong> {selectedUserData.phone}</p>
              </div>
            )}
          </div>

          <div className="form-group">
            <label>Chọn Voucher:</label>
            <select
              value={selectedVoucher}
              onChange={(e) => setSelectedVoucher(e.target.value)}
              className="select-dropdown"
            >
              <option value="">-- Chọn voucher --</option>
              {vouchers.map(voucher => (
                <option key={voucher.id} value={voucher.id}>
                  {voucher.code} - Giảm {voucher.discountPercent}%
                </option>
              ))}
            </select>
            {selectedVoucherData && (
              <div className="selected-info voucher-info">
                <p><strong>Mã Code:</strong> {selectedVoucherData.code}</p>
                <p><strong>Giảm:</strong> {selectedVoucherData.discountPercent}%</p>
                <p><strong>Số Lượng Còn:</strong> {selectedVoucherData.quantity}</p>
                <p>
                  <strong>Trạng Thái:</strong>
                  <span className={`status ${selectedVoucherData.status?.toLowerCase() || 'active'}`}>
                    {selectedVoucherData.status || 'ACTIVE'}
                  </span>
                </p>
              </div>
            )}
          </div>

          <button
            className="btn-use-voucher"
            onClick={handleUseVoucher}
            disabled={!selectedUser || !selectedVoucher}
          >
            Sử Dụng Voucher
          </button>
        </div>
      </div>

      <div className="usage-history">
        <h3>Lịch Sử Sử Dụng Voucher</h3>
        {loading ? (
          <p className="loading">Đang tải...</p>
        ) : usages.length === 0 ? (
          <p className="no-data">Chưa có lịch sử sử dụng</p>
        ) : (
          <table className="usages-table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Người Dùng</th>
                <th>Mã Voucher</th>
                <th>Ngày Sử Dụng</th>
              </tr>
            </thead>
            <tbody>
              {usages.map(usage => (
                <tr key={usage.id}>
                  <td>{usage.id}</td>
                  <td>{usage.userName}</td>
                  <td>{usage.voucherCode}</td>
                  <td>{new Date(usage.usedDate).toLocaleDateString('vi-VN')}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}

export default VoucherUsage;
