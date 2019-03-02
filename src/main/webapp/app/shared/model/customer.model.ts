import { IShippingRegion } from 'app/shared/model/shipping-region.model';

export interface ICustomer {
    id?: number;
    creditCard?: string;
    address1?: string;
    address2?: string;
    city?: string;
    region?: string;
    postalCode?: string;
    country?: string;
    dayPhone?: string;
    evePhone?: string;
    mobPhone?: string;
    shippingRegions?: IShippingRegion[];
}

export class Customer implements ICustomer {
    constructor(
        public id?: number,
        public creditCard?: string,
        public address1?: string,
        public address2?: string,
        public city?: string,
        public region?: string,
        public postalCode?: string,
        public country?: string,
        public dayPhone?: string,
        public evePhone?: string,
        public mobPhone?: string,
        public shippingRegions?: IShippingRegion[]
    ) {}
}
