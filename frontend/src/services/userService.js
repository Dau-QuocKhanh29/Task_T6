const API_BASE = 'http://localhost:8080';

// Lấy danh sách user
export const getUsers = async () => {
  try {
    const response = await fetch(`${API_BASE}/users`);
    if (!response.ok) throw new Error('Failed to fetch users');
    const data = await response.json();
    return data.data || [];
  } catch (error) {
    console.error('Error fetching users:', error);
    throw error;
  }
};

// Tạo user mới
export const createUser = async (userData) => {
  try {
    const response = await fetch(`${API_BASE}/users`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    });
    if (!response.ok) throw new Error('Failed to create user');
    const data = await response.json();
    return data.data || data;
  } catch (error) {
    console.error('Error creating user:', error);
    throw error;
  }
};

// Cập nhật user
export const updateUser = async (id, userData) => {
  try {
    const response = await fetch(`${API_BASE}/users/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData),
    });
    if (!response.ok) throw new Error('Failed to update user');
    const data = await response.json();
    return data.data || data;
  } catch (error) {
    console.error('Error updating user:', error);
    throw error;
  }
};

// Xóa user
export const deleteUser = async (id) => {
  try {
    const response = await fetch(`${API_BASE}/users/${id}`, {
      method: 'DELETE',
    });
    if (!response.ok) throw new Error('Failed to delete user');
    const data = await response.json();
    return data.data || data;
  } catch (error) {
    console.error('Error deleting user:', error);
    throw error;
  }
};
