import {useEffect} from "react";
import Cookies from "js-cookie";

const lk = `https://lk.firestorm.team`;

export default function CheckCookies({children}) {
    useEffect(() => {
        const email = "email";
        const id = "id";
        const token = "token";
        const username = "username";

        const emailCookie = Cookies.get(email);
        const idCookie = Cookies.get(id);
        const tokenCookie = Cookies.get(token);
        const usernameCookie = Cookies.get(username);

        console.log(emailCookie);
        console.log(idCookie);
        console.log(tokenCookie);
        console.log(usernameCookie);
        // debugger;

        if (!tokenCookie) {
            window.location.href = lk;
        }
    }, []);

    return children;
};
