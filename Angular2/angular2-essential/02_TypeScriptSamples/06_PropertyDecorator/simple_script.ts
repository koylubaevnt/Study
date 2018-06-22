
function Overide(label: string) {
    return function(target: any, key: string) {
        Object.defineProperty(target, key, {
            configurable: false,
            get: () => label
        })
    }
}


class Test {
    
    @Overide('Hello world')
    name: string = '...';

}

let test = new Test();
test.name = 'TEST';
console.log(test.name);


function ReadOnly(target: any, key: string) {
    Object.defineProperty(target, key, { writable: false });
}

class Test2 {
    @ReadOnly
    name: string;
}

let test2 = new Test2();
test2.name = 'Ivan';
console.log(test2.name);
