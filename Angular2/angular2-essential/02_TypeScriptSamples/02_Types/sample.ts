

let isDone: boolean = true;
let value: number = 10;
let stringValue: string;

let list1:number[] = [1, 2, 3];
let list2: Array<number> = [1, 2, 3];

enum Color {
    Red = 1,
    Green = 2,
    Blue = 3
};

let c: Color = Color.Green;
let t: Color = 1;
console.log(c);
console.log(t);

let someValue: any = 4;
someValue = 'test string';
someValue = false;

function showMessage(data: string): void {
    alert(data);
}

showMessage('hello');