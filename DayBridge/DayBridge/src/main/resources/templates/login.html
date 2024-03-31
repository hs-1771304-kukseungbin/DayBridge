import React, {useState} from 'react';
import '../../css/Login.css';

function Login() {
    const [userID, setUserId] = useState('');
    const [userPW, setUserPW] = useState('');

    const handleLogin = async (event) => {
        event.preventDefault();

        // 서버로 전송될 데이터
        const data = {
            userID: userID,
            userPW: userPW
        };

        try {
            const response = await fetch('/login', {
                method: 'POST',
                headers: {
                    'Content=Type': 'application/json'
                },
                body: JSON.stringify(data) // 데이터를 JSON 문자열로 변환해 전송
            });

            if(response.ok) {
                const user = await response.json();
                console.log('로그인 성공: ', user);
                window.location.href="/home.html";
            } else {
                console.error('로그인 실패: ', response.statusText);
                alert('아이디 혹은 비밀번호를 다시 확인해 주세요.');
            }
        }
        catch (error) {
            console.error('로그인 중 에러:', error);
            alert('오류 발생');
        }
    };

    return (
        <div>
            <h1>로그인</h1>
            <form onSubmit={handleLogin}>
                <div className="container">
                    <label htmlFor="userID">아이디: </label>
                    <input type="text" id="userID" name="userID" required value="userID" onChange={(e) => setUserId(e.target.value)} />
                </div>
                <div className="container">
                    <label htmlFor="userPW">비밀번호: </label>
                    <input type="password" id="userPW" name="userPW" required value="userPW" onChange={(e) => setUserPW(e.target.value)} />
                </div>
                <div className="container">
                    <button className="sign_in" type="submit">로그인</button>
                </div>
            </form>
        </div>
    );
}

export default Login;
