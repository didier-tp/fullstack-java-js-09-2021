import { Component, OnInit } from '@angular/core';
import { Virement } from '../common/data/virement';

@Component({
  selector: 'app-virement',
  templateUrl: './virement.component.html',
  styleUrls: ['./virement.component.scss']
})
export class VirementComponent implements OnInit {

  //saisir des paramétre du virement 
  //qui sera déléguer au WS REST java/spring`
  virement : Virement = new Virement();
  message : string = "";

  onVirement(){
    //v1: on va afficher les valeurs saisies
    this.message = "valeurs saisies=" + JSON.stringify(this.virement);
    //V2 : deleguation du virement au WS REST/java
  }

  constructor() { }

  ngOnInit(): void {
  }

}
