import { Component } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'timer-host',
    templateUrl: 'timer-host.component.html'
})
export class TimerHostComponent {

    tickHandler1(event) {
        console.log('Tick Handler 1 - ' + event);
    }

    tickHandler2(event) {
        console.log('Tick Handler 2 - ' + event);
    }
    
}