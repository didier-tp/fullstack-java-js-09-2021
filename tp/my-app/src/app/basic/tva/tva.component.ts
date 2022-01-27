import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tva',
  templateUrl: './tva.component.html',
  styleUrls: ['./tva.component.scss']
})
export class TvaComponent implements OnInit {

  ht : number =0;
  taux : number = 20;//par defaut (en %)
  listeTaux : number[]= [ 5 , 10 , 20]; //en %
  tva : number =0;
  ttc : number =0;
  
  onCalculerTvaEtTtc(){
     this.tva = this.ht * this.taux / 100;
     //console.log("tva="+this.tva);
     this.ttc = this.tva + this.ht;
  }

  constructor() { }

  ngOnInit(): void {
  }

}
