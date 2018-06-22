import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OnInitComponent } from './on-init/on-init.component';
import { OnInitDemoComponent } from './on-init/on-init-demo.component';
import { OnChangesComponent } from './on-changes/on-changes.component';
import { OnChangesDemoComponent } from './on-changes/on-changes-demo.component';
import { CommentComponent } from './differs/comment.component';
import { CommentsListComponent } from './differs/comments-list.component';
import { DiffersDemoComponent } from './differs/differs-demo.component';
import { AllHooksComponent } from './all-hooks/all-hooks.component';
import { AllHooksDemoComponent } from './all-hooks/all-hooks-demo.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [OnInitComponent, OnInitDemoComponent, OnChangesComponent, OnChangesDemoComponent, CommentComponent, CommentsListComponent, DiffersDemoComponent, AllHooksComponent, AllHooksDemoComponent]
})
export class LifecycleModule { }
