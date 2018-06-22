import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { Counter, Status } from '../counter';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { CounterService } from '../counter.service';
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-counter-detail',
  templateUrl: './counter-detail.component.html',
  styleUrls: ['./counter-detail.component.css']
})
export class CounterDetailComponent implements OnInit, OnDestroy {

  @Input()
  counter: Counter;

  btnChangeStatus = 'none';
  isRunning: boolean;
  subscription: Subscription;

  constructor( private route: ActivatedRoute,
    private counterService: CounterService,
    private location: Location) { }

  ngOnInit() {
    this.getCounter();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getCounter(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    const poolData = this.counterService.getCounter(id.toString());
    this.subscription = poolData.expand(() => Observable.timer(1000).concatMap(() => poolData))
      .subscribe(counter => {
        console.log(counter);
        this.counter = counter;
        this.refreshStatus();
        console.log(Status.RUNNING.valueOf(), Status[counter.status]);
        if (Status.STOPPED.toString() === Status[counter.status].toString()) {
          this.subscription.unsubscribe();
        }
      });
  }

  start(): void {
    if (this.isRunning) {
      this.counterService.pause(this.counter.id)
        .subscribe(counter => {
          if (counter) {
            this.counter = counter;
            this.refreshStatus();
            this.subscription.unsubscribe();
          }
        });
    } else {
      this.counterService.start(this.counter.id)
        .subscribe(counter => {
          if (counter) {
            this.counter = counter;
            this.refreshStatus();
            this.getCounter();
          }
        });
    }
  }

  stop(): void {
    this.counterService.stop(this.counter.id)
    .subscribe(counter => {
      if (counter) {
        this.counter = counter;
        this.refreshStatus();
        this.subscription.unsubscribe();
      }
    });
  }

  goBack(): void {
    this.location.back();
  }

  refreshStatus(): void {
    // console.log(Status[this.counter.status], Status.RUNNING);
    if (Status[this.counter.status].toString() === Status.RUNNING.toString()) {
      this.btnChangeStatus = 'pause';
      this.isRunning = true;
    } else {
      this.btnChangeStatus = 'start';
      this.isRunning = false;
    }
  }
}
