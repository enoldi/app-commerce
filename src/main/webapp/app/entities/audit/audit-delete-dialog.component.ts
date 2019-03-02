import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAudit } from 'app/shared/model/audit.model';
import { AuditService } from './audit.service';

@Component({
    selector: 'jhi-audit-delete-dialog',
    templateUrl: './audit-delete-dialog.component.html'
})
export class AuditDeleteDialogComponent {
    audit: IAudit;

    constructor(protected auditService: AuditService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'auditListModification',
                content: 'Deleted an audit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-delete-popup',
    template: ''
})
export class AuditDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ audit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AuditDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.audit = audit;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/audit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/audit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
