
class Greeter {

    greeting: string;

    constructor(message: string) {
        this.greeting = message;
    }

    greet(): string{
        return 'Hello, ' + this.greeting;
    }

}

let greeter: Greeter = new Greeter("word");
greeter.greet();

// Интерфейсы "утиная типизация"
function printLabel(labelledObj: { label: string }) {
    console.log(labelledObj.label);
}

let myObj = { size: 10, label: 'Size 10 Object' };
printLabel(myObj);

interface LabelledValue {
    label: string;
}

function printLabel2(labelledObj: LabelledValue) {
    console.log(labelledObj.label);
}

printLabel2(myObj);
