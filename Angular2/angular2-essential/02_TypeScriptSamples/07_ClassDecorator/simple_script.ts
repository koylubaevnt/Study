
function testClassDecorator() {
    return (target) => {

        let newCostructor: any = function() {
            target.apply(this);
            this.newProp = 123;
        }

        return newCostructor;
    }
}

@testClassDecorator()
class Test3 {
    prop1 = 10;
}

let test3 = new Test3();
console.log(test3)