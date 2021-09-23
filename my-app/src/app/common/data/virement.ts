export class Virement{
    constructor(public montant :number|undefined=undefined,
                public numCptDeb:number|undefined=undefined,
                public numCptCred:number|undefined=undefined,
                public statut:boolean|undefined=undefined,
                public message:string=""){

                }
}