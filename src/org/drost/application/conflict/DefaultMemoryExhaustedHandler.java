/*
 * This file is part of the application library that simplifies common
 * initialization and helps setting up any java program.
 * 
 * Copyright (C) 2016 Yannick Drost, all rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.drost.application.conflict;

import java.lang.management.MemoryPoolMXBean;

/**
 * @author kimschorat
 *
 */
public class DefaultMemoryExhaustedHandler extends AbstractMemoryExhaustedHandler
{

	/* (non-Javadoc)
	 * @see org.drost.application.handler.conflict.ConflictHandler#handle(org.drost.application.handler.conflict.ConflictInfo)
	 */
	@Override
	public void handle( ConflictInfo<MemoryPoolMXBean> info )
	{
		System.gc();
	}

}
