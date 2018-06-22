import { Component, OnInit, Input, Output, EventEmitter, KeyValueDiffers, DoCheck } from '@angular/core';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: []
})
export class CommentComponent implements OnInit, DoCheck {

  @Input() comment: any;
  @Output() onRemove: EventEmitter<any>;
  differ: any;

  constructor(differs: KeyValueDiffers) {
    this.differ = differs.find([]).create(null);
    this.onRemove = new EventEmitter();
   }

  ngOnInit(): void {
  }

  ngDoCheck(): void {
    const changes = this.differ.diff(this.comment);

    if(changes) {
      changes.forEachAddedItem(i => this.logChange('added', i));
      changes.forEachRemovedItem(i => this.logChange('removed', i));
      changes.forEachChangedItem(i => this.logChange('changed', i));
    }
  }

  logChange(action: string, item: any): void {
    if(action === 'changed') {
      console.log(item.key, action, 'from', item.previousValue, 'to', item.currentValue);
    }
    if(action === 'added') {
      console.log(action, item.key, 'with', item.currentValue);
    }
    if(action === 'removed') {
      console.log( action, item.key,'(was' + item.previousValue + ')');
    }
  }

  remove(): void {
    this.onRemove.emit(this.comment);
  }

  clear(): void {
    delete this.comment.comment;
  }

  like(): void {
    this.comment.likes += 1;
  }
}
