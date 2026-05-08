# Admin Dashboard - Quản Lý Người Dùng & Voucher

Giao diện Admin quản lý người dùng, voucher và sử dụng voucher.

## Cấu Trúc Dự Án

```
src/
├── components/
│   ├── Dashboard.jsx          # Điều hướng chính & sidebar
│   ├── Users.jsx              # Quản lý người dùng
│   ├── Vouchers.jsx           # Quản lý voucher
│   └── VoucherUsage.jsx       # Sử dụng voucher
├── services/
│   ├── userService.js         # API calls cho Users
│   ├── voucherService.js      # API calls cho Vouchers
│   └── voucherUsageService.js # API calls cho VoucherUsage
├── styles/
│   ├── Dashboard.css          # Styles cho Dashboard
│   ├── Users.css              # Styles cho Users
│   ├── Vouchers.css           # Styles cho Vouchers
│   └── VoucherUsage.css       # Styles cho VoucherUsage
├── App.jsx
└── App.css
```

## Tính Năng

### 1. Quản Lý Người Dùng 👥
- Xem danh sách người dùng
- Tạo người dùng mới (họ tên, email, số điện thoại)
- Chỉnh sửa thông tin người dùng
- Xóa người dùng
- Tìm kiếm người dùng

**Attributes:**
- Họ Tên
- Email
- Số Điện Thoại

### 2. Quản Lý Voucher 🎟️
- Xem danh sách voucher
- Tạo voucher mới
- Chỉnh sửa voucher
- Xóa voucher
- Tìm kiếm voucher theo mã code

**Attributes:**
- Mã Code
- Phần Trăm Giảm
- Số Lượng
- Ngày Tạo
- Trạng Thái (ACTIVE/INACTIVE)

### 3. Sử Dụng Voucher ✨
- Chọn người dùng từ dropdown
- Chọn voucher từ dropdown
- Xem thông tin chi tiết khi chọn
- Nút "Sử Dụng Voucher" để thực hiện giao dịch
- Xem lịch sử sử dụng voucher

## API Integration

Tất cả các API calls được gom trong thư mục `services/`:

### User APIs
```
GET    /api/users              - Lấy danh sách user
POST   /api/users              - Tạo user mới
PUT    /api/users/{id}         - Cập nhật user
DELETE /api/users/{id}         - Xóa user
```

### Voucher APIs
```
GET    /api/vouchers           - Lấy danh sách voucher
POST   /api/vouchers           - Tạo voucher mới
PUT    /api/vouchers/{id}      - Cập nhật voucher
DELETE /api/vouchers/{id}      - Xóa voucher
GET    /api/vouchers/search?code=ABC - Tìm kiếm voucher
```

### Voucher Usage APIs
```
GET    /api/voucher-usages     - Xem lịch sử sử dụng
POST   /api/voucher-usages     - Sử dụng voucher
```

## Installation

```bash
npm install
```

## Development

```bash
npm run dev
```

## Build

```bash
npm run build
```

## Linting

```bash
npm run lint
```

## Thay Đổi Base URL API

Mở tệp `src/services/userService.js`, `voucherService.js` hoặc `voucherUsageService.js` và thay đổi giá trị `API_BASE`:

```javascript
const API_BASE = 'http://localhost:3000/api';
```

## Responsive Design

Giao diện hoàn toàn responsive với các breakpoints:
- Desktop: 1024px+
- Tablet: 768px - 1023px
- Mobile: < 768px

## Công Nghệ Sử Dụng

- React 19
- Vite
- CSS3 (Flexbox & Grid)
- Fetch API

## Notes

- Tất cả errors sẽ hiển thị thông qua alert dialogs
- Success messages sẽ hiển thị với animation
- Form validation được thực hiện phía client
- Xóa items cần xác nhận từ người dùng
