import { Moment } from 'moment';
import { IProduct } from 'app/shared/model/product.model';

export interface IShoppingCard {
    id?: number;
    itemId?: number;
    attributes?: string;
    quantity?: number;
    buyNow?: boolean;
    addedOn?: Moment;
    salary?: number;
    products?: IProduct[];
}

export class ShoppingCard implements IShoppingCard {
    constructor(
        public id?: number,
        public itemId?: number,
        public attributes?: string,
        public quantity?: number,
        public buyNow?: boolean,
        public addedOn?: Moment,
        public salary?: number,
        public products?: IProduct[]
    ) {
        this.buyNow = this.buyNow || false;
    }
}
