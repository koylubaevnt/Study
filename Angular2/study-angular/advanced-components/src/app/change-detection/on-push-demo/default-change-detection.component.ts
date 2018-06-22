import { Component, OnInit, Input } from '@angular/core';

import { Profile } from './profile.model';

@Component({
  selector: 'app-default-change-detection',
  templateUrl: './default-change-detection.component.html',
  styleUrls: []
})
export class DefaultChangeDetectionComponent implements OnInit {

  @Input() profile: Profile;
  
  constructor() { }

  ngOnInit() {
  }

}
