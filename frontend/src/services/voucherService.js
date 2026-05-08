const API_BASE = 'http://localhost:8080';

// Lấy danh sách voucher
export const getVouchers = async () => {
  try {
    const response = await fetch(`${API_BASE}/vouchers`);
    if (!response.ok) throw new Error('Failed to fetch vouchers');
    const data = await response.json();
    return data.data || [];
  } catch (error) {
    console.error('Error fetching vouchers:', error);
    throw error;
  }
};

// Tạo voucher mới
export const createVoucher = async (voucherData) => {
  try {
    const response = await fetch(`${API_BASE}/vouchers`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(voucherData),
    });
    if (!response.ok) throw new Error('Failed to create voucher');
    const data = await response.json();
    return data.data || data;
  } catch (error) {
    console.error('Error creating voucher:', error);
    throw error;
  }
};

// Cập nhật voucher
export const updateVoucher = async (id, voucherData) => {
  try {
    const response = await fetch(`${API_BASE}/vouchers/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(voucherData),
    });
    if (!response.ok) throw new Error('Failed to update voucher');
    const data = await response.json();
    return data.data || data;
  } catch (error) {
    console.error('Error updating voucher:', error);
    throw error;
  }
};

// Xóa voucher
export const deleteVoucher = async (id) => {
  try {
    const response = await fetch(`${API_BASE}/vouchers/${id}`, {
      method: 'DELETE',
    });
    if (!response.ok) throw new Error('Failed to delete voucher');
    const data = await response.json();
    return data.data || data;
  } catch (error) {
    console.error('Error deleting voucher:', error);
    throw error;
  }
};

// Tìm kiếm voucher
export const searchVouchers = async (code) => {
  try {
    const response = await fetch(`${API_BASE}/vouchers/search?code=${code}`);
    if (!response.ok) throw new Error('Failed to search vouchers');
    const data = await response.json();
    return data.data || [];
  } catch (error) {
    console.error('Error searching vouchers:', error);
    throw error;
  }
};
