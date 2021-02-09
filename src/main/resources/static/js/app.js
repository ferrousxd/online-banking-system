async function getUsers() {
    const query = document.getElementById('query').value;
    const url = 'http://127.0.0.1:8080/api/users?query=' + query;
    const response = await fetch(url);
    return await response.json();
}

async function renderUsers() {
    const users = await getUsers();
    let results = '';
    users.forEach(user => {
        results +=
            `<tr>
                <td>${user.id}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
            </tr>`
    });
    const searchResults = document.getElementById('searchResults');
    searchResults.innerHTML = results;
}

async function getTransactions() {
    const url = 'http://127.0.0.1:8080/api/transactions';
    const response = await fetch(url);
    return await response.json();
}

async function renderTransactions() {
    const transactions = await getTransactions();
    let results = '';
    transactions.forEach(transaction => {
        results +=
            `<tr>
                <td>${transaction.amount} KZT</td>
                <td>${transaction.date}</td>
                <td>${transaction.card.number}</td>
            </tr>`
    });
    const transactionsTableBody = document.getElementById('transactions');
    transactionsTableBody.innerHTML = results;
}