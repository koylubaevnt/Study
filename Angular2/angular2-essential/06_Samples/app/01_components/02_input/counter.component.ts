import { Component, Input } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'counter',
    templateUrl: 'counter.component.html',
    inputs: ['name']
})
export class CounterComponent {
    name: string = 'default name'; // данное свойство можно задавать с помощью кода <counter name="Counter Name"></counter>

    @Input()
    counterValue: number = 0; // данное свойство можно задавать с помощью кода <counter counterValue="5"></counter>

    @Input('step')
    counterStep: number = 1; // данное свойство можно задавать с помощью кода <counter step="2"></counter>

    increment() {
        this.counterValue = this.counterValue + this.counterStep;
    }
}
