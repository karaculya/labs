function calculate() {
    let form = document.getElementById('dataForm');
    let num1 = parseFloat(form.num1.value);
    let num2 = parseFloat(form.num2.value);
    let operation = form.operation.value;
    let res;
    let operationSymbol;
    switch (operation) {
        case 'add':
            res = num1 + num2;
            operationSymbol = '+';
            break;
        case 'subtract':
            res = num1 - num2;
            operationSymbol = '−';
            break;
        case 'multiply':
            res = num1 * num2;
            operationSymbol = '×';
            break;
        case 'divide':
            if(num2!= 0) {
                res = num1 / num2;
                operationSymbol = '÷';
            }
            else{
                alert("Деление на ноль");
                return false;
            }
            break;
        default:
            alert("Выберите операцию");
            return false;
    }
    localStorage.setItem('num1', num1);
    localStorage.setItem('num2', num2);
    localStorage.setItem('operation', operationSymbol);
    localStorage.setItem('result', res);
    return true;
}


document.addEventListener('DOMContentLoaded', () => {
    const num1 = localStorage.getItem('num1');
    const num2 = localStorage.getItem('num2');
    const operation = localStorage.getItem('operation');
    const result = localStorage.getItem('result');
    if (num1 && num2 && operation && result) {
        document.getElementById('num1').textContent = num1;
        document.getElementById('num2').textContent = num2;
        document.getElementById('operation').textContent = operation;
        document.getElementById('result').textContent = result;
    }
});
