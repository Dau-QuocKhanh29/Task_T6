import { useState, useEffect } from 'react';
import { getVouchers, createVoucher, updateVoucher, deleteVoucher, searchVouchers } from '../services/voucherService';
import '../styles/Vouchers.css';

function Vouchers() {
  const [vouchers, setVouchers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [showForm, setShowForm] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [formData, setFormData] = useState({
    code: '',
    discountPercent: '',
    quantity: '',
    expiredDate: new Date().toISOString().split('T')[0],
    status: 'ACTIVE',
  });
  const [searchTerm, setSearchTerm] = useState('');

  // Load danh sách voucher
  useEffect(() => {
    loadVouchers();
  }, []);

  const loadVouchers = async () => {
    setLoading(true);
    try {
      const data = await getVouchers();
      setVouchers(Array.isArray(data) ? data : []);
    } catch (error) {
      console.error('Error loading vouchers:', error);
      alert('Lỗi tải danh sách voucher');
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const submitData = {
        ...formData,
        discountPercent: parseFloat(formData.discountPercent),
        quantity: parseInt(formData.quantity),
      };

      if (editingId) {
        await updateVoucher(editingId, submitData);
        alert('Cập nhật voucher thành công');
      } else {
        await createVoucher(submitData);
        alert('Tạo voucher thành công');
      }
      resetForm();
      loadVouchers();
    } catch (error) {
      alert('Lỗi: ' + error.message);
    }
  };

  const handleEdit = (voucher) => {
    setFormData(voucher);
    setEditingId(voucher.id);
    setShowForm(true);
  };

  const handleDelete = async (id) => {
    if (window.confirm('Bạn có chắc chắn muốn xóa voucher này?')) {
      try {
        await deleteVoucher(id);
        alert('Xóa voucher thành công');
        loadVouchers();
      } catch (error) {
        alert('Lỗi: ' + error.message);
      }
    }
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!searchTerm.trim()) {
      loadVouchers();
      return;
    }
    setLoading(true);
    try {
      const data = await searchVouchers(searchTerm);
      setVouchers(Array.isArray(data) ? data : []);
    } catch (error) {
      alert('Lỗi tìm kiếm: ' + error.message);
    } finally {
      setLoading(false);
    }
  };

  const resetForm = () => {
    setFormData({
      code: '',
      discountPercent: '',
      quantity: '',
      expiredDate: new Date().toISOString().split('T')[0],
      status: 'ACTIVE',
    });
    setEditingId(null);
    setShowForm(false);
  };

  const handleCancel = () => {
    resetForm();
  };

  return (
    <div className="vouchers-container">
      <div className="vouchers-header">
        <h2>Quản Lý Voucher</h2>
        <button 
          className="btn-add" 
          onClick={() => setShowForm(!showForm)}
        >
          + Thêm Voucher
        </button>
      </div>

      {showForm && (
        <form className="form-container" onSubmit={handleSubmit}>
          <div className="form-row">
            <div className="form-group">
              <label>Mã Code:</label>
              <input
                type="text"
                name="code"
                value={formData.code}
                onChange={handleInputChange}
                placeholder="Nhập mã voucher"
                required
              />
            </div>
            <div className="form-group">
              <label>Phần Trăm Giảm (%):</label>
              <input
                type="number"
                name="discountPercent"
                value={formData.discountPercent}
                onChange={handleInputChange}
                placeholder="Nhập phần trăm giảm"
                min="0"
                max="100"
                required
              />
            </div>
          </div>
          <div className="form-row">
            <div className="form-group">
              <label>Số Lượng:</label>
              <input
                type="number"
                name="quantity"
                value={formData.quantity}
                onChange={handleInputChange}
                placeholder="Nhập số lượng"
                min="0"
                required
              />
            </div>
            <div className="form-group">
              <label>Ngày Hết Hạn:</label>
              <input
                type="date"
                name="expiredDate"
                value={formData.expiredDate}
                onChange={handleInputChange}
                required
              />
            </div>
          </div>
          <div className="form-group">
            <label>Trạng Thái:</label>
            <select
              name="status"
              value={formData.status}
              onChange={handleInputChange}
            >
              <option value="ACTIVE">ACTIVE</option>
              <option value="INACTIVE">INACTIVE</option>
            </select>
          </div>
          <div className="form-actions">
            <button type="submit" className="btn-save">
              {editingId ? 'Cập Nhật' : 'Tạo Mới'}
            </button>
            <button type="button" className="btn-cancel" onClick={handleCancel}>
              Hủy
            </button>
          </div>
        </form>
      )}

      <form className="search-container" onSubmit={handleSearch}>
        <input
          type="text"
          placeholder="Tìm kiếm theo mã code..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-input"
        />
        <button type="submit" className="btn-search">Tìm Kiếm</button>
      </form>

      {loading ? (
        <p className="loading">Đang tải...</p>
      ) : vouchers.length === 0 ? (
        <p className="no-data">Không có dữ liệu</p>
      ) : (
        <table className="vouchers-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Mã Code</th>
              <th>Phần Trăm Giảm</th>
              <th>Số Lượng</th>
              <th>Ngày Hết Hạn</th>
              <th>Trạng Thái</th>
              <th>Hành Động</th>
            </tr>
          </thead>
          <tbody>
            {vouchers.map(voucher => (
              <tr key={voucher.id}>
                <td>{voucher.id}</td>
                <td>{voucher.code}</td>
                <td>{voucher.discountPercent}%</td>
                <td>{voucher.quantity}</td>
                <td>{new Date(voucher.expiredDate).toLocaleDateString('vi-VN')}</td>
                <td>
                  <span className={`status ${voucher.status?.toLowerCase() || 'active'}`}>
                    {voucher.status || 'ACTIVE'}
                  </span>
                </td>
                <td className="actions">
                  <button 
                    className="btn-edit"
                    onClick={() => handleEdit(voucher)}
                  >
                    Sửa
                  </button>
                  <button 
                    className="btn-delete"
                    onClick={() => handleDelete(voucher.id)}
                  >
                    Xóa
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default Vouchers;
