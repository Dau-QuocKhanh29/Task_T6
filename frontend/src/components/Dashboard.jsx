import { useState } from 'react';
import Users from './Users';
import Vouchers from './Vouchers';
import VoucherUsage from './VoucherUsage';
import '../styles/Dashboard.css';

function Dashboard() {
  const [activeTab, setActiveTab] = useState('users');

  const renderContent = () => {
    switch(activeTab) {
      case 'users':
        return <Users />;
      case 'vouchers':
        return <Vouchers />;
      case 'usage':
        return <VoucherUsage />;
      default:
        return <Users />;
    }
  };

  return (
    <div className="dashboard">
      <aside className="sidebar">
        <div className="sidebar-header">
          <h1>Admin Panel</h1>
        </div>
        <nav className="sidebar-nav">
          <button
            className={`nav-item ${activeTab === 'users' ? 'active' : ''}`}
            onClick={() => setActiveTab('users')}
          >
            <span className="icon">👥</span>
            <span>Quản Lý Người Dùng</span>
          </button>
          <button
            className={`nav-item ${activeTab === 'vouchers' ? 'active' : ''}`}
            onClick={() => setActiveTab('vouchers')}
          >
            <span className="icon">🎟️</span>
            <span>Quản Lý Voucher</span>
          </button>
          <button
            className={`nav-item ${activeTab === 'usage' ? 'active' : ''}`}
            onClick={() => setActiveTab('usage')}
          >
            <span className="icon">✨</span>
            <span>Sử Dụng Voucher</span>
          </button>
        </nav>
      </aside>

      <main className="main-content">
        {renderContent()}
      </main>
    </div>
  );
}

export default Dashboard;
