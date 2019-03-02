import { IProduct } from 'app/shared/model/product.model';
import { IOrders } from 'app/shared/model/orders.model';

export interface IOrderItems {
    id?: number;
    attributes?: string;
    productName?: string;
    quantity?: number;
    unityCost?: number;
    products?: IProduct[];
    order?: IOrders;
}

export class OrderItems implements IOrderItems {
    constructor(
        public id?: number,
        public attributes?: string,
        public productName?: string,
        public quantity?: number,
        public unityCost?: number,
        public products?: IProduct[],
        public order?: IOrders
    ) {}
}
