interface Shape {
    name: string;
    printArea: () => void;
}

class Rectangle implements Shape {
    
    name: string = 'Rectangle';

    height: number;
    width: number;

    constructor(h: number, w: number) {
        this.height = h;
        this.width = w;
    }

    printArea() {
        let area: number = this.height * this.width;
        console.log(`Area of ${this.name} is ${area}`);
    }
}

class Circle implements Shape {
    
    name: string = 'Circle';

    radius: number;
    width: number;

    constructor(r: number) {
        this.radius = r;
    }

    printArea() {
        let area: number = Math.pow(this.radius, 2);
        console.log(`Area of ${this.name} is ${area}`);
    }
}

let shapes: Array<Shape> = new Array<Shape>();
shapes[0] = new Rectangle(100, 200);
shapes[1] = new Circle(20);
for(let i = 0; i < shapes.length; i++) {
    let currentShape = shapes[i];
    currentShape.printArea();
}