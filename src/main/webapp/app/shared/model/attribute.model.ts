import { IProduct } from 'app/shared/model/product.model';

export interface IAttribute {
    id?: number;
    name?: string;
    products?: IProduct[];
}

export class Attribute implements IAttribute {
    constructor(public id?: number, public name?: string, public products?: IProduct[]) {}
}
