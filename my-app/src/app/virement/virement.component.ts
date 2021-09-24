import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  virement : Virement = new Virement(10,1,2);
  message : string = "";
  ok : boolean = false;

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
    console.log("virementEffectue=" + JSON.stringify(virementEffectue))
     if(virementEffectue.statut==true){
       this.message="ok " + virementEffectue.message;
       this.ok=true;
     }else{
      this.message="echec " + virementEffectue.message;
      this.ok=false;
     }
  }

  onNaviguerListeCommptes(){
    this._router.navigateByUrl("/ngr-comptes");
  }

  constructor(private _compteService : CompteService,
              private _router : Router) { }

  ngOnInit(): void {
  }

}
