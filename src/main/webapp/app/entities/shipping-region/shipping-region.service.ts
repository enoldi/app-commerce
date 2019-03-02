import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IShippingRegion } from 'app/shared/model/shipping-region.model';

type EntityResponseType = HttpResponse<IShippingRegion>;
type EntityArrayResponseType = HttpResponse<IShippingRegion[]>;

@Injectable({ providedIn: 'root' })
export class ShippingRegionService {
    public resourceUrl = SERVER_API_URL + 'api/shipping-regions';

    constructor(protected http: HttpClient) {}

    create(shippingRegion: IShippingRegion): Observable<EntityResponseType> {
        return this.http.post<IShippingRegion>(this.resourceUrl, shippingRegion, { observe: 'response' });
    }

    update(shippingRegion: IShippingRegion): Observable<EntityResponseType> {
        return this.http.put<IShippingRegion>(this.resourceUrl, shippingRegion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IShippingRegion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IShippingRegion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
