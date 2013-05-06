/**
 * galaxy inc.
 * meetup client for android
 */
package com.galaxy.meetup.client.android.api;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;

import com.galaxy.meetup.client.android.content.EsAccount;
import com.galaxy.meetup.client.android.content.EsSquaresData;
import com.galaxy.meetup.client.android.network.PlusiOperation;
import com.galaxy.meetup.client.android.network.http.HttpOperation;
import com.galaxy.meetup.server.client.domain.request.EditSquareMembershipOzRequest;
import com.galaxy.meetup.server.client.domain.response.EditSquareMembershipOzResponse;
import com.galaxy.meetup.server.client.v2.request.Request;
import com.galaxy.meetup.server.client.v2.response.Response;

/**
 * 
 * @author sihai
 *
 */
public class EditSquareMembershipOperation extends PlusiOperation {

	private final String mAction;
    private boolean mIsBlockingModerator;
    private final String mSquareId;
    
    public EditSquareMembershipOperation(Context context, EsAccount esaccount, String s, String s1, Intent intent, HttpOperation.OperationListener operationlistener)
    {
        super(context, esaccount, "editsquaremembership", intent, operationlistener, EditSquareMembershipOzResponse.class);
        mSquareId = s;
        mAction = s1;
    }

    public final boolean getIsBlockingModerator()
    {
        return mIsBlockingModerator;
    }

    protected final void handleResponse(Response response) throws IOException
    {
        mIsBlockingModerator = ((EditSquareMembershipOzResponse)response).isViewerBlockingModerator.booleanValue();
        EsSquaresData.updateSquareMembership(mContext, mAccount, mSquareId, mAction);
    }

    protected final Request populateRequest()
    {
        EditSquareMembershipOzRequest editsquaremembershipozrequest = new EditSquareMembershipOzRequest();
        editsquaremembershipozrequest.obfuscatedSquareId = mSquareId;
        editsquaremembershipozrequest.action = mAction;
        return editsquaremembershipozrequest;
    }
}
