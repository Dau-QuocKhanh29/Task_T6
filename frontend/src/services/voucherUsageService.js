const API_BASE = 'http://localhost:8080';

// Lấy danh sách lịch sử sử dụng voucher
export const getVoucherUsages = async () => {
  try {
    const response = await fetch(`${API_BASE}/voucher-usages`);
    if (!response.ok) throw new Error('Failed to fetch voucher usages');
    const data = await response.json();
    return data.data || [];
  } catch (error) {
    console.error('Error fetching voucher usages:', error);
    throw error;
  }
};

// Sử dụng voucher cho user
export const useVoucher = async (usageData) => {
  try {
    const response = await fetch(`${API_BASE}/voucher-usages`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(usageData),
    });
    if (!response.ok) throw new Error('Failed to use voucher');
    const data = await response.json();
    return data.data || data;
  } catch (error) {
    console.error('Error using voucher:', error);
    throw error;
  }
};
