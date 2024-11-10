const logout = () => {
    const jwtToken = localStorage.getItem('jwtToken');

    if (!jwtToken) {
        alert('Không có token để đăng xuất.');
        return;
    }

    fetch('/auth/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + jwtToken
        },
        body: JSON.stringify({ token: jwtToken })
    })
        .then(response => {
            if (response.ok) {
                localStorage.removeItem('jwtToken');
                localStorage.removeItem('username');
                alert('Đăng xuất thành công.');
                window.location.href = '/login';
            } else {
                alert('Đăng xuất không thành công.');
            }
        })
        .catch(error => {
            console.error('Lỗi đăng xuất:', error);
            alert('Đã xảy ra lỗi khi đăng xuất. Vui lòng thử lại sau.');
        });
};