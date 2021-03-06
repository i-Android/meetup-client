/**
 * galaxy inc.
 * meetup client for android
 */
package com.galaxy.meetup.client.android.api;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.Intent;

import com.galaxy.meetup.client.android.content.EsAccount;
import com.galaxy.meetup.client.android.content.EsPhotosDataApiary;
import com.galaxy.meetup.client.android.network.PlusiOperation;
import com.galaxy.meetup.client.android.network.http.HttpOperation;
import com.galaxy.meetup.server.client.domain.request.DeletePhotosRequest;
import com.galaxy.meetup.server.client.domain.response.DeletePhotosResponse;
import com.galaxy.meetup.server.client.v2.request.Request;
import com.galaxy.meetup.server.client.v2.response.Response;

/**
 * 
 * @author sihai
 *
 */
public class DeletePhotosOperation extends PlusiOperation {

	private final List mPhotoIds;
	
	public DeletePhotosOperation(Context context, EsAccount esaccount, List list, Intent intent, HttpOperation.OperationListener operationlistener)
    {
        super(context, esaccount, "deletephotos", intent, operationlistener, DeletePhotosResponse.class);
        mPhotoIds = list;
    }

    protected final void handleResponse(Response response) throws IOException
    {
        DeletePhotosResponse deletephotosresponse = (DeletePhotosResponse)response;
        onStartResultProcessing();
        if(deletephotosresponse.success.booleanValue())
        {
            Context context = mContext;
            EsAccount esaccount = mAccount;
            mAccount.getGaiaId();
            EsPhotosDataApiary.deletePhotos(context, esaccount, mPhotoIds);
        }
    }

    protected final Request populateRequest()
    {
    	DeletePhotosRequest response = new DeletePhotosRequest();
    	response.photoId = mPhotoIds;
    	return response;
    }

}
