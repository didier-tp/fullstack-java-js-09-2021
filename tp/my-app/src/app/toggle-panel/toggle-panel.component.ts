import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'toggle-panel',
  templateUrl: './toggle-panel.component.html',
  styleUrls: ['./toggle-panel.component.scss']
})
export class TogglePanelComponent implements OnInit {

  toggleP : boolean =false;
  
  @Input()
  title : string = 'default panel title';

  constructor() { }

  ngOnInit(): void {
  }

}
