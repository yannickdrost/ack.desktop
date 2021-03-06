/*
 * This file is part of the application library that simplifies common 
 * initialization and helps setting up any java program.
 * 
 * Copyright (C) 2016 Yannick Drost, all rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.drost.application.conflict;

/**
 * Handles all uncaught exceptions application wide. Whenever an exception
 * is thrown because any unexpected state change occurs, this handler will
 * get informed by invoking its {@link #handle(ConflictInfo)} method. There
 * is no need to call this method directly.
 * 
 * <p>
 * This class implements the {@link ConflictHandler} interface. The
 * generic class parameter is defined by the {@link Throwable} class type.
 * </p>
 * 
 * @author kimschorat
 * @since 1.0
 * 
 * @see ConflictHandler
 */
@SuppressWarnings("serial")
public abstract class AbstractExceptionHandler implements ConflictHandler<Throwable>
{
	boolean registered = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void register( )
	{
		if(isRegistered())
			return;
		
		/*
		 * Handle uncaught exceptions by informing the applications exception
		 * handler.
		 */
		Thread.setDefaultUncaughtExceptionHandler( new Thread.UncaughtExceptionHandler( )
		{
			@Override
			public void uncaughtException( Thread t, Throwable e )
			{
				handle( new ConflictInfo<Throwable>( e, t ) );
			}
		} );
		
		for(AbstractExceptionHandler eh : ConflictManager.getConflictManager( ).getExceptionHandlers( ))
		{
			eh.unregister( );
		}
		
		ConflictManager.getConflictManager( ).getExceptionHandlers( ).add( this );
		
		registered = true;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregister( )
	{
		Thread.setDefaultUncaughtExceptionHandler( null );

//		ConflictManager.getConflictManager( ).getExceptionHandlers( ).remove( this );
		
		registered = false;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRegistered()
	{
		return registered;
	}
}
