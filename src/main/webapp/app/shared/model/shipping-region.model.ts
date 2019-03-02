import { ICustomer } from 'app/shared/model/customer.model';
import { IShipping } from 'app/shared/model/shipping.model';

export interface IShippingRegion {
    id?: number;
    name?: string;
    customer?: ICustomer;
    shipping?: IShipping;
}

export class ShippingRegion implements IShippingRegion {
    constructor(public id?: number, public name?: string, public customer?: ICustomer, public shipping?: IShipping) {}
}
