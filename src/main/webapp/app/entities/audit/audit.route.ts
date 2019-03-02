import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Audit } from 'app/shared/model/audit.model';
import { AuditService } from './audit.service';
import { AuditComponent } from './audit.component';
import { AuditDetailComponent } from './audit-detail.component';
import { AuditUpdateComponent } from './audit-update.component';
import { AuditDeletePopupComponent } from './audit-delete-dialog.component';
import { IAudit } from 'app/shared/model/audit.model';

@Injectable({ providedIn: 'root' })
export class AuditResolve implements Resolve<IAudit> {
    constructor(private service: AuditService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAudit> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Audit>) => response.ok),
                map((audit: HttpResponse<Audit>) => audit.body)
            );
        }
        return of(new Audit());
    }
}

export const auditRoute: Routes = [
    {
        path: '',
        component: AuditComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Audits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AuditDetailComponent,
        resolve: {
            audit: AuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Audits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AuditUpdateComponent,
        resolve: {
            audit: AuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Audits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AuditUpdateComponent,
        resolve: {
            audit: AuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Audits'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AuditDeletePopupComponent,
        resolve: {
            audit: AuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Audits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
