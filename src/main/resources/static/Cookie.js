function setCookie() {
    fetch("/cookie/setCookie", {
        method: "GET"
    })
        .then(response => {
            if (response.ok) {
                getCookie();
            }
        });
}

function getCookie() {
    fetch("/cookie/getCookie", {
        method: "GET"
    });
}

function setCookieValue() {
    fetch("/cookie/getCookieValue?cookieName=user", {
        method: "GET"
    })
        .then(data => {
            fetch("/cookie/saveData", {
                method: "POST",
                body: JSON.stringify({
                    user: data
                }),
                headers: {
                    "Content-type": "application/json"
                }
            });
        });
}
