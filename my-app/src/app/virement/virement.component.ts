import { Component, OnInit } from '@angular/core';
import { Virement } from '../common/data/virement';
import { CompteService } from '../common/service/compte.service';

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
    //this.message = "valeurs saisies=" + JSON.stringify(this.virement);
    //V2 : deleguation du virement au WS REST/java
    this._compteService.effectuerVirement$(this.virement)
        .subscribe(
          (virementEffectue)=>{ this.gererVirementEffectue(virementEffectue)},
          (err)=>{console.log(err); this.message="echec virement"}
        );
  }

  gererVirementEffectue(virementEffectue : Virement){
     if(virementEffectue.statut==true){
       this.message="ok " + virementEffectue.message;
     }else{
      this.message="echec " + virementEffectue.message;
     }
  }

  constructor(private _compteService : CompteService) { }

  ngOnInit(): void {
  }

}
