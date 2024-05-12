// shopping-list.js
const apiUrl = 'http://localhost:8080/api/shopping-list';

class ShoppingList {
    constructor() {
        this.shoppingList = [];
        this.fetchShoppingList();
    }

    fetchShoppingList() {
        fetch(apiUrl)
          .then(response => response.json())
          .then(data => {
                this.shoppingList = data;
                this.renderShoppingList();
            });
    }

    addItem(item) {
        fetch(apiUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(item)
        })
          .then(response => response.json())
          .then(data => {
                this.shoppingList.push(data);
                this.renderShoppingList();
            });
    }

    deleteItem(id) {
        fetch(`${apiUrl}/${id}`, { method:'DELETE' })
          .then(() => {
                this.shoppingList = this.shoppingList.filter(item => item.id!== id);
                this.renderShoppingList();
            });
    }

    markAsPurchased(id) {
        fetch(`${apiUrl}/${id}`, { method: 'PATCH' })
          .then(response => response.json())
          .then(data => {
                const item = this.shoppingList.find(item => item.id === id);
                item.purchased = true;
                this.renderShoppingList();
            });
    }

    markAsNotPurchased(id) {
        fetch(`${apiUrl}/${id}`, { method: 'PATCH', body: JSON.stringify({ purchased: false }) })
          .then(response => response.json())
          .then(data => {
                const item = this.shoppingList.find(item => item.id === id);
                item.purchased = false;
                this.renderShoppingList();
            });
    }

    renderShoppingList() {
        const listElement = document.getElementById('shopping-list');
        listElement.innerHTML = '';
        this.shoppingList.forEach(item => {
            const itemElement = document.createElement('li');
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.checked = item.purchased;
            checkbox.onchange = () => {
                if (checkbox.checked) {
                    this.markAsPurchased(item.id);
                } else {
                    this.markAsNotPurchased(item.id);
                }
            };
            itemElement.appendChild(checkbox);

            const itemText = document.createTextNode(`${item.name} `);
            itemElement.appendChild(itemText);

            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'Delete';
            deleteButton.onclick = () => this.deleteItem(item.id);
            itemElement.appendChild(deleteButton);

            listElement.appendChild(itemElement);
        });
    }
}

const shoppingList = new ShoppingList();

document.getElementById('add-item-form').addEventListener('submit', event => {
    event.preventDefault();
    const itemName = document.getElementById('item-name').value;
    const newItem = { name: itemName };
    shoppingList.addItem(newItem);
    document.getElementById('item-name').value = '';
});