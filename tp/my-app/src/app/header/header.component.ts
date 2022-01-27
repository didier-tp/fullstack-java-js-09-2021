import { Component, Input, OnInit } from '@angular/core';
import { PreferencesService } from '../common/service/preferences.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Input()
  titre :string = "titre_header"; //valeur par défaut

  constructor(public preferencesService : PreferencesService) { 
    console.log("dans constructor, titre="+this.titre);
  }

  ngOnInit(): void {
    //ressemble a @PostConstruct de java
    console.log("dans ngOnInit, titre="+this.titre);
  }

}
