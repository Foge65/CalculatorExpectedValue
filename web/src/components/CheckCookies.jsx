import {useEffect, useState} from "react";
import Cookies from "js-cookie";

const lk = `https://lk.firestorm.team`;

export default function CheckCookies({children}) {
    const [isAuthenticated, setIsAuthenticated] = useState(true);

    useEffect(() => {
        if (!Cookies.get("token")) {
            setIsAuthenticated(false);
        }
    }, []);

    if (!isAuthenticated) {
        return (
            <div className="auth-container">
                <p>Для работы с калькулятором, вы должны быть авторизованы в личном кабинете</p>
                <button className="btnAuth" onClick={() => window.location.href = lk}>Авторизоваться через личный
                    кабинет
                </button>
            </div>
        );
    }

    return children;
};
