import { Component, OnInit, OnDestroy } from '@angular/core';
import { Counter } from '../counter';
import { CounterService } from '../counter.service';
import { Observable } from 'rxjs/Rx';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-counters',
  templateUrl: './counters.component.html',
  styleUrls: ['./counters.component.css']
})
export class CountersComponent implements OnInit, OnDestroy {

  counters: Counter[];
  constructor(private counterService: CounterService) { }

  subscription: Subscription;

  ngOnInit() {
    this.getCounters();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getCounters(): void {
      const poolData = this.counterService.getCounterList();
      this.subscription = poolData.expand(() => Observable.timer(1000).concatMap(() => poolData))
      .subscribe(counters => {
        this.counters = counters;
      });
  }

  add(maxValue: number): void {
    this.counterService.addCounter(maxValue)
      .subscribe(counter => {
        console.log(counter);
        if (counter) {
          this.counters.push(counter);
        }
      });
  }

  delete(counter: Counter): void {
    this.counterService.deleteCounter(counter.id)
      .subscribe(data => {
        if (data) {
          this.counters.splice(this.counters.indexOf(data), 1);
        }
      });
  }
}
