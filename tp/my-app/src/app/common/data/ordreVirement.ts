export class OrdreVirement {
    constructor(
        public montant : number ,
        public numCptDeb : number = 0,
        public numCptCred : number = 0,
        public ok : boolean | undefined = undefined ,
        public message : string =""
    ){}
}